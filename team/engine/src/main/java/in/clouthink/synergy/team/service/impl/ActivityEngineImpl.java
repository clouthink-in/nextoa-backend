package in.clouthink.synergy.team.service.impl;

import in.clouthink.daas.edm.Edms;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.DefaultTaskNotifyRequest;
import in.clouthink.synergy.team.domain.request.TaskNotifyRequest;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.repository.*;
import in.clouthink.synergy.team.service.ActivityEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 */
@Service
public class ActivityEngineImpl implements ActivityEngine {

    private static final Log logger = LogFactory.getLog(ActivityEngineImpl.class);

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityActionRepository activityActionRepository;

    @Autowired
    private ActivityTransitionRepository activityTransitionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RecentUserRelationshipRepository recentUserRelationshipRepository;

    @Override
    public void markActivityAsRead(String id, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        markActivityAsRead(activity, user);
    }

    @Override
    public void markActivityAsRead(Activity activity, User user) {
        ActivityAction readActivityAction = activityActionRepository.findFirstByActivityAndTypeAndCreatedBy(activity,
                                                                                                         ActivityActionType.READ,
                                                                                                         user);
        if (readActivityAction == null) {
            readActivityAction = new ActivityAction();
            readActivityAction.setActivity(activity);
            readActivityAction.setType(ActivityActionType.READ);
            readActivityAction.setCreatedBy(user);
            readActivityAction.setCreatedAt(new Date());
        }
        readActivityAction.setModifiedAt(new Date());
        activityActionRepository.save(readActivityAction);

        Task task = taskRepository.findByBizRefIdAndReceiver(activity.getId(), user);
        if (task != null) {
            taskRepository.markAsRead(task.getId());
        }

        int readCounter = activityActionRepository.countByActivityAndType(activity, ActivityActionType.READ);
        activityRepository.updateReadCounter(activity.getId(), readCounter);
    }

    @Override
    public void handleStartActivityAction(ActivityAction startActivityAction) {
        startActivityAction = activityActionRepository.save(startActivityAction);

        Activity activity = startActivityAction.getActivity();
        activity.setAllowedActions(startActivityAction.getAllowedActions());
        activity.setStatus(ActivityStatus.IN_PROGRESS);
        activity.setStartActivityAction(startActivityAction);
        activity.setLatestActivityAction(startActivityAction);
        activity.setStartedAt(new Date());
        activityRepository.save(activity);

        handleMessage(startActivityAction);

        //create message for the starter ( and make its status as processed )
        Task task = taskRepository.findByBizRefIdAndReceiver(activity.getId(), activity.getCreatedBy());
        if (task == null) {
            Date now = new Date();
            task = new Task();
            task.setCategory(activity.getCategory());
            task.setTitle(activity.getTitle());
            task.setBizRefType(BizRefType.PAPER);
            task.setBizRefId(activity.getId());
            task.setActionRefId(startActivityAction.getId());
            task.setInitiator(activity.getCreatedBy());
            task.setSender(activity.getCreatedBy());
            task.setReceiver(activity.getCreatedBy());
            task.setReceivedAt(now);
            task.setModifiedAt(now);
            task.setStatus(TaskStatus.PROCESSED);
            taskRepository.save(task);
        }

        //handle the transition
        ActivityTransition activityTransition = new ActivityTransition();
        activityTransition.setId(startActivityAction.getId());
        activityTransition.setActivity(activity);
        activityTransition.setActivityActionType(startActivityAction.getType());
        activityTransition.setCreatorId(startActivityAction.getCreatedBy().getId());
        activityTransition.setCreatedBy(startActivityAction.getCreatedBy());
        activityTransition.setCreatedAt(startActivityAction.getCreatedAt());
        activityTransition.setCurrentActionId(startActivityAction.getId());
        activityTransition.setParticipantIds(resolveParticipants(startActivityAction));
        activityTransitionRepository.save(activityTransition);
    }

    @Override
    public void handleReplyActivityAction(ActivityAction previousActivityAction, ActivityAction replyActivityAction) {
        Activity reloadActivity = activityRepository.findById(replyActivityAction.getActivity().getId());
        if (reloadActivity.getStatus() == ActivityStatus.REVOKED) {
            processFailedActivityAction(replyActivityAction, "协作请求已经被撤回,不能进行回复操作.");
            return;
        }

        replyActivityAction = activityActionRepository.save(replyActivityAction);

        Activity activity = replyActivityAction.getActivity();
        if (Activity.isEditAllowed(activity) && !StringUtils.isEmpty(replyActivityAction.getActivityContent())) {
            String crc32NewValue = Activity.crc32(replyActivityAction.getActivityContent());
            if (!crc32NewValue.equals(activity.getContentCrc32())) {
                activity.setContent(replyActivityAction.getActivityContent());
            }
        }
        activity.setLatestActivityAction(replyActivityAction);
        activityRepository.save(activity);

        handleMessage(replyActivityAction);

        //handle the transition
        ActivityTransition previousActivityTransition = activityTransitionRepository.findById(previousActivityAction.getId());
        previousActivityTransition.setNextActionId(replyActivityAction.getId());
        activityTransitionRepository.save(previousActivityTransition);

        ActivityTransition activityTransition = new ActivityTransition();
        activityTransition.setId(replyActivityAction.getId());
        activityTransition.setActivity(activity);
        activityTransition.setActivityActionType(replyActivityAction.getType());
        activityTransition.setCreatorId(replyActivityAction.getCreatedBy().getId());
        activityTransition.setCreatedBy(replyActivityAction.getCreatedBy());
        activityTransition.setCreatedAt(replyActivityAction.getCreatedAt());
        activityTransition.setCurrentActionId(replyActivityAction.getId());
        activityTransition.setPreviousActionId(previousActivityAction.getId());
        activityTransition.setParticipantIds(resolveParticipants(replyActivityAction));
        activityTransitionRepository.save(activityTransition);
    }

    @Override
    public void handleForwardActivityAction(ActivityAction previousActivityAction, ActivityAction forwardActivityAction) {
        Activity reloadActivity = activityRepository.findById(forwardActivityAction.getActivity().getId());
        if (reloadActivity.getStatus() == ActivityStatus.REVOKED) {
            processFailedActivityAction(forwardActivityAction, "协作请求已经被撤回,不能进行转发操作.");
            return;
        }

        forwardActivityAction = activityActionRepository.save(forwardActivityAction);

        Activity activity = forwardActivityAction.getActivity();
        if (Activity.isEditAllowed(activity) && !StringUtils.isEmpty(forwardActivityAction.getActivityContent())) {
            String crc32NewValue = Activity.crc32(forwardActivityAction.getActivityContent());
            if (!crc32NewValue.equals(activity.getContentCrc32())) {
                activity.setContent(forwardActivityAction.getActivityContent());
            }
        }
        activity.setLatestActivityAction(forwardActivityAction);
        activityRepository.save(activity);

        handleMessage(forwardActivityAction);

        //handle the transition
        ActivityTransition previousActivityTransition = activityTransitionRepository.findById(previousActivityAction.getId());
        previousActivityTransition.setNextActionId(forwardActivityAction.getId());
        activityTransitionRepository.save(previousActivityTransition);

        ActivityTransition activityTransition = new ActivityTransition();
        activityTransition.setId(forwardActivityAction.getId());
        activityTransition.setActivity(activity);
        activityTransition.setActivityActionType(forwardActivityAction.getType());
        activityTransition.setCreatorId(forwardActivityAction.getCreatedBy().getId());
        activityTransition.setCreatedBy(forwardActivityAction.getCreatedBy());
        activityTransition.setCreatedAt(forwardActivityAction.getCreatedAt());
        activityTransition.setCurrentActionId(forwardActivityAction.getId());
        activityTransition.setPreviousActionId(previousActivityAction.getId());
        activityTransition.setParticipantIds(resolveParticipants(forwardActivityAction));
        activityTransitionRepository.save(activityTransition);
    }

    @Override
    public void handleRevokeActivityAction(ActivityAction revokeActivityAction) {
        Activity reloadActivity = activityRepository.findById(revokeActivityAction.getActivity().getId());
        if (!reloadActivity.getStartActivityAction().getId().equals(reloadActivity.getLatestActivityAction().getId())) {
            processFailedActivityAction(revokeActivityAction, "协作请求已被处理,不能进行撤回操作.");
            return;
        }

        //clean the activity message
        List<Task> taskList = taskRepository.findByBizRefId(reloadActivity.getId());
        for (Task task : taskList) {
            if (!task.getReceiver().getId().equalsIgnoreCase(reloadActivity.getCreatedBy().getId()) &&
                    task.getStatus() == TaskStatus.PROCESSED) {
                processFailedActivityAction(revokeActivityAction, "协作请求已被处理,不能进行撤回操作.");
                return;
            }
        }
        taskRepository.delete(taskList);

        //clean the activity actions
        activityActionRepository.delete(activityActionRepository.findListByActivity(reloadActivity));

        reloadActivity.setStatus(ActivityStatus.REVOKED);
        reloadActivity.setRevokedAt(new Date());
        reloadActivity.setModifiedAt(new Date());
        //撤回后,重置action,恢复和草稿一样的状态
        reloadActivity.setStartActivityAction(null);
        reloadActivity.setLatestActivityAction(null);
        activityRepository.save(reloadActivity);
    }

    @Override
    public void handleEndActivityAction(ActivityAction endActivityAction) {
        Activity reloadActivity = activityRepository.findById(endActivityAction.getActivity().getId());
        if (reloadActivity.getStatus() == ActivityStatus.REVOKED) {
            processFailedActivityAction(endActivityAction, "协作请求已经被撤回,不能进行结束操作.");
            return;
        }

        activityActionRepository.save(endActivityAction);

        Task task = taskRepository.findByBizRefIdAndReceiver(endActivityAction.getActivity().getId(),
                                                             endActivityAction.getCreatedBy());
        if (task != null) {
            task.setStatus(TaskStatus.ENDED);
            task.setModifiedAt(new Date());
            taskRepository.save(task);
        }
    }

    @Override
    public void handleTerminateActivityAction(ActivityAction activityAction) {
        Activity reloadActivity = activityRepository.findById(activityAction.getActivity().getId());
        if (reloadActivity.getStatus() != ActivityStatus.TERMINATED) {
            activityActionRepository.save(activityAction);

            reloadActivity.setStatus(ActivityStatus.TERMINATED);
            reloadActivity.setModifiedAt(new Date());
            activityRepository.save(reloadActivity);
        }

        List<Task> taskList = taskRepository.findByBizRefId(activityAction.getActivity().getId());
        taskList.stream().forEach(message -> {
            if (message.getStatus() != TaskStatus.TERMINATED) {
                message.setStatus(TaskStatus.TERMINATED);
                message.setModifiedAt(new Date());
                taskRepository.save(message);
            }
        });
    }

    //***************************
    // private
    //***************************
    private void handleMessage(ActivityAction activityAction) {
        //
        Task task = taskRepository.findByBizRefIdAndReceiver(activityAction.getActivity().getId(),
                                                             activityAction.getCreatedBy());
        if (task != null) {
            task.setSender(activityAction.getCreatedBy());
            task.setActionRefId(activityAction.getId());
            task.setStatus(TaskStatus.PROCESSED);
            task.setModifiedAt(new Date());
            taskRepository.save(task);
        }

        Activity activity = activityAction.getActivity();
        List<Receiver> toReceivers = activityAction.getToReceivers();
        List<Receiver> ccReceivers = activityAction.getCcReceivers();


        List<TaskNotifyRequest> taskNotifyRequests = new ArrayList<>();
        List<Task> tasks = createMessageForReceiver(activity,
                                                    activityAction,
                                                    toReceivers,
                                                    ccReceivers,
                                                    taskNotifyRequests);
//		messageRepository.save(messages);

        handleRecentUsers(activityAction.getCreatedBy(),
                          tasks.stream().map(msg -> msg.getReceiver()).collect(Collectors.toList()));

//		if (toReceivers != null) {
//			buildMessageNotifyRequest(activityAction, activity, toReceivers, messageNotifyRequests);
//		}
//		if (ccReceivers != null) {
//			buildMessageNotifyRequest(activityAction, activity, ccReceivers, messageNotifyRequests);
//		}

        Edms.getEdm("sms").dispatch(TaskNotifyRequest.MESSAGE_NOTIFY, taskNotifyRequests);
    }

    /**
     * Keep the recent 10 contact users
     *
     * @param byWho
     * @param recentUsers
     */
    private void handleRecentUsers(User byWho, List<User> recentUsers) {
        if (recentUsers == null || recentUsers.isEmpty()) {
            return;
        }
        try {
            List<RecentUserRelationship> recentUserRelationships = recentUserRelationshipRepository.findListByUserOrderByCreatedAtDesc(
                    byWho);
            //key : userId , value : relationId
            Map<String, RecentUserRelationship> relationshipMap = new HashMap<>();
            Set<String> existedUserIds = new HashSet<>();
            recentUserRelationships.stream().forEach(relationship -> {
                existedUserIds.add(relationship.getRecentUser().getId());
                relationshipMap.put(relationship.getRecentUser().getId(), relationship);
            });

            recentUsers.stream().forEach(recentUser -> {
                if (!existedUserIds.contains(recentUser.getId())) {
                    RecentUserRelationship recentUserRelationship = new RecentUserRelationship();
                    recentUserRelationship.setUser(byWho);
                    recentUserRelationship.setRecentUser(recentUser);
                    recentUserRelationship.setCreatedAt(new Date());
                    recentUserRelationshipRepository.save(recentUserRelationship);
                }
                else {
                    RecentUserRelationship recentUserRelationship = relationshipMap.get(recentUser.getId());
                    if (recentUserRelationship != null) {
                        recentUserRelationship.setCreatedAt(new Date());
                        recentUserRelationshipRepository.save(recentUserRelationship);
                    }
                }
            });

            //remove the over stack users
            recentUserRelationships = recentUserRelationshipRepository.findListByUserOrderByCreatedAtDesc(byWho);
            int totalSize = recentUserRelationships.size();
            int overStackSize = recentUserRelationships.size() - 10;
            for (int i = 0; i < overStackSize; i++) {
                RecentUserRelationship overRelationship = recentUserRelationships.remove(totalSize - i - 1);
                recentUserRelationshipRepository.delete(overRelationship);
            }
        } catch (Exception e) {
        }
    }

    private void processFailedActivityAction(ActivityAction activityAction, String errorMessage) {
        activityAction.setFailed(true);
        activityAction.setErrorMessage(errorMessage);
        activityActionRepository.save(activityAction);
        logger.warn(errorMessage);
    }

//	private void buildMessageNotifyRequest(ActivityAction activityAction,
//										   Activity activity,
//										   Message message,
//										   List<Receiver> receivers,
//										   List<MessageNotifyRequest> messageNotifyRequests) {
//		receivers.stream().forEach(receiver -> {
//			if (receiver.isNotifyEnabled()) {
//				DefaultMessageNotifyRequest messageNotifyRequest = new DefaultMessageNotifyRequest();
//				messageNotifyRequest.setCellphone(receiver.getUser().getContactPhone());
//				messageNotifyRequest.setMessageId(message.getId());
//				messageNotifyRequest.setMessageSender(activityAction.getCreatedBy().getUsername());
//				messageNotifyRequest.setMessageTitle(activity.getTitle());
//				messageNotifyRequests.add(messageNotifyRequest);
//			}
//		});
//	}


    private List<Task> createMessageForReceiver(Activity activity,
                                                ActivityAction activityAction,
                                                List<Receiver> toReceivers,
                                                List<Receiver> ccReceivers,
                                                List<TaskNotifyRequest> taskNotifyRequests) {
        List<Task> tasks = new ArrayList<>();
        Set<String> userIds = new HashSet<>();

        doCreateMessageForReceiver(activity, activityAction, toReceivers, tasks, userIds, taskNotifyRequests);

        if (ccReceivers != null) {
            doCreateMessageForReceiver(activity, activityAction, ccReceivers, tasks, userIds, taskNotifyRequests);
        }

        return tasks;
    }

    private void doCreateMessageForReceiver(Activity activity,
                                            ActivityAction activityAction,
                                            List<Receiver> receivers,
                                            List<Task> tasks,
                                            Set<String> userIds,
                                            List<TaskNotifyRequest> taskNotifyRequests) {
        receivers.stream().forEach(receiver -> {
            String userId = receiver.getUser().getId();
            if (!userIds.contains(userId)) {
                User fromUser = activityAction.getCreatedBy();
                User toUser = receiver.getUser();
                Task task = createOrUpdateMessage(activity, activityAction, fromUser, toUser);

                if (receiver.isNotifyEnabled()) {
                    DefaultTaskNotifyRequest messageNotifyRequest = new DefaultTaskNotifyRequest();
                    messageNotifyRequest.setCellphone(toUser.getCellphone());
                    messageNotifyRequest.setMessageId(task.getId());
                    messageNotifyRequest.setMessageSender(activityAction.getCreatedBy().getUsername());
                    messageNotifyRequest.setMessageTitle(activity.getTitle());
                    taskNotifyRequests.add(messageNotifyRequest);
                }

                tasks.add(task);
            }
            userIds.add(userId);
        });
    }

    private Task createOrUpdateMessage(Activity activity, ActivityAction activityAction, User sender, User toUser) {
        Task task = taskRepository.findByBizRefIdAndReceiver(activity.getId(), toUser);
        Date now = new Date();
        if (task == null) {
            task = new Task();
            task.setCategory(activity.getCategory());
            task.setTitle(activity.getTitle());
            task.setBizRefType(BizRefType.PAPER);
            task.setBizRefId(activity.getId());
            task.setActionRefId(activityAction.getId());
            task.setInitiator(activity.getCreatedBy());
            task.setSender(sender);
            task.setReceiver(toUser);
            task.setReceivedAt(now);
            task.setModifiedAt(now);
            task.setStatus(TaskStatus.PENDING);
        }
        else {
            task.setActionRefId(activityAction.getId());
            task.setInitiator(activity.getCreatedBy());
            task.setSender(sender);
            task.setReceivedAt(now);
            task.setModifiedAt(now);
            task.setStatus(TaskStatus.PENDING);
        }
        return taskRepository.save(task);
    }

    private List<String> resolveParticipants(ActivityAction activityAction) {
        List<String> result = new ArrayList<>();
        if (activityAction == null) {
            return result;
        }

        List<Receiver> to = activityAction.getToReceivers();
        if (to != null) {
            to.stream().forEach(receiver -> result.add(receiver.getUser().getId()));
        }

        List<Receiver> cc = activityAction.getCcReceivers();
        if (cc != null) {
            cc.stream().forEach(receiver -> result.add(receiver.getUser().getId()));
        }

        return result;
    }

}
