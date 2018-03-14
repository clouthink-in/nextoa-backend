package in.clouthink.synergy.team.engine.business.impl;

import com.google.common.collect.Lists;
import in.clouthink.synergy.account.domain.model.SysRole;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.*;
import in.clouthink.synergy.team.engine.business.TeamService;
import in.clouthink.synergy.team.exception.ActivityException;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.repository.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @auther dz
 */
@Service
public class TeamServiceImpl implements TeamService {

    private static final Log logger = LogFactory.getLog(TeamServiceImpl.class);

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
    public void startActivity(String id, StartActivityRequest request, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        if (ActivityStatus.DRAFT != activity.getStatus()) {
            if (StringUtils.isEmpty(activity.getTitle())) {
                throw new ActivityException("标题不能为空");
            }

            if (StringUtils.isEmpty(activity.getContent())) {
                throw new ActivityException("内容不能为空");
            }
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (activity.getStatus() == ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("该协作请求已经进入流转,请勿重复提交");
        }

        List<Receiver> toReceivers = request.getToReceivers();
        if (toReceivers == null || toReceivers.isEmpty()) {
            throw new ActivityException("请选择主送人后再提交协作请求.");
        }

        List<Receiver> ccReceivers = request.getCcReceivers();

        List<ActivityActionType> disabledActions = request.getDisabledActions();
        List<ActivityActionType> allowedActions = Lists.newArrayList(ActivityActionType.PRINT,
                                                                     ActivityActionType.FORWARD,
                                                                     ActivityActionType.EDIT,
                                                                     ActivityActionType.COPY);
        disabledActions.stream().forEach(disabledAction -> allowedActions.remove(disabledAction));

        ActivityAction startActivityAction = new ActivityAction();
        startActivityAction.setActivity(activity);
        startActivityAction.setType(ActivityActionType.START);
        startActivityAction.setToReceivers(toReceivers);
        startActivityAction.setCcReceivers(ccReceivers);
        startActivityAction.setAllowedActions(allowedActions);
        startActivityAction.setCreatedBy(user);
        startActivityAction.setCreatedAt(new Date());

        startActivityAction = activityActionRepository.save(startActivityAction);

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
    public void replyActivity(String id, ReplyActivityRequest request, User user) {
        Task task = taskRepository.findByBizRefIdAndReceiver(id, user);

        if (task != null && task.getStatus() == TaskStatus.PROCESSED) {
            throw new ActivityException("该协作请求任务已经处理过了,不能重复处理.");
        }

        Activity activity = activityRepository.findById(id);

        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (activity.getStatus() == ActivityStatus.REVOKED) {
            throw new ActivityException("该协作请求已撤回,不能回复");
        }

        if (activity.getStatus() != ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("该协作请求未进入流转,不能回复");
        }

        List<Receiver> toReceivers = request.getToReceivers();
        if (toReceivers == null || toReceivers.isEmpty()) {
            throw new ActivityException("请选择回复人.");
        }

        List<Receiver> ccReceivers = request.getCcReceivers();

        ActivityAction replyActivityAction = new ActivityAction();
        replyActivityAction.setActivity(activity);
        if (Activity.isEditAllowed(activity)) {
            replyActivityAction.setActivityContent(request.getActivityContent());
        }
        replyActivityAction.setType(ActivityActionType.REPLY);
        replyActivityAction.setToReceivers(toReceivers);
        replyActivityAction.setCcReceivers(ccReceivers);
        replyActivityAction.setContent(request.getContent());
        replyActivityAction.setCreatedBy(user);
        replyActivityAction.setCreatedAt(new Date());

        ActivityAction previousActivityAction = resolveActivityAction(request.getMessageId());

        replyActivityAction = activityActionRepository.save(replyActivityAction);

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
    public void forwardActivity(String id, ForwardActivityRequest request, User user) {
        Task task = taskRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null && task.getStatus() == TaskStatus.PROCESSED) {
            throw new ActivityException("该协作请求任务已经处理过了,不能重复处理.");
        }

        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }
        if (activity.getStatus() == ActivityStatus.REVOKED) {
            throw new ActivityException("该协作请求已撤回,不能转发");
        }
        if (activity.getStatus() != ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("该协作请求未进入流转,不能转发");
        }

        List<ActivityActionType> allowedActions = activity.getAllowedActions();
        if (allowedActions == null || !allowedActions.contains(ActivityActionType.FORWARD)) {
            throw new ActivityException("该协作请求禁止转发.");
        }

        List<Receiver> toReceivers = request.getToReceivers();
        if (toReceivers == null || toReceivers.isEmpty()) {
            throw new ActivityException("请选择主送人后再转发协作请求.");
        }

        List<Receiver> ccReceivers = request.getCcReceivers();

        ActivityAction forwardActivityAction = new ActivityAction();
        forwardActivityAction.setActivity(activity);
        if (Activity.isEditAllowed(activity)) {
            forwardActivityAction.setActivityContent(request.getActivityContent());
        }
        forwardActivityAction.setType(ActivityActionType.FORWARD);
        forwardActivityAction.setToReceivers(toReceivers);
        forwardActivityAction.setCcReceivers(ccReceivers);
        forwardActivityAction.setContent(request.getContent());
        forwardActivityAction.setCreatedBy(user);
        forwardActivityAction.setCreatedAt(new Date());

        ActivityAction previousActivityAction = resolveActivityAction(request.getMessageId());

        forwardActivityAction = activityActionRepository.save(forwardActivityAction);

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
    public void revokeActivity(String id, String reason, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (!activity.getCreatedBy().getId().equalsIgnoreCase(user.getId())) {
            throw new ActivityException("只能协作请求的创建人能撤回协作请求");
        }

        if (activity.getStatus() != ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("只能撤回已流转的协作请求");
        }

        if (!activity.getStartActivityAction().getId().equals(activity.getLatestActivityAction().getId())) {
            throw new ActivityException("协作请求已被处理,不能进行撤回操作.");
        }

        List<Task> taskList = taskRepository.findByBizRefId(activity.getId());
        for (Task task : taskList) {
            if (!task.getReceiver().getId().equalsIgnoreCase(activity.getCreatedBy().getId()) &&
                    task.getStatus() == TaskStatus.PROCESSED) {
                throw new ActivityException("协作请求已被处理,不能进行撤回操作.");
            }
        }
        taskRepository.delete(taskList);

        //clean the activity actions
        activityActionRepository.delete(activityActionRepository.findListByActivity(activity));

        activity.setStatus(ActivityStatus.REVOKED);
        activity.setRevokedAt(new Date());
        activity.setModifiedAt(new Date());
        //撤回后,重置action,恢复和草稿一样的状态
        activity.setStartActivityAction(null);
        activity.setLatestActivityAction(null);
        activityRepository.save(activity);
    }

    @Override
    public void endActivity(String id, String reason, User user) {
        Task task = taskRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null && task.getStatus() == TaskStatus.PROCESSED) {
            throw new ActivityException("该协作请求任务已经处理过了,不能重复处理.");
        }

        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (activity.getStatus() == ActivityStatus.REVOKED) {
            throw new ActivityException("该协作请求已撤回,不能结束");
        }

        if (activity.getStatus() != ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("该协作请求未进入流转,不能结束");
        }

        ActivityAction endActivityAction = new ActivityAction();
        endActivityAction.setActivity(activity);
        endActivityAction.setType(ActivityActionType.END);
        endActivityAction.setCreatedBy(user);
        endActivityAction.setCreatedAt(new Date());
        activityActionRepository.save(endActivityAction);

        task.setStatus(TaskStatus.ENDED);
        task.setModifiedAt(new Date());
        taskRepository.save(task);
    }

    @Override
    public void terminateActivity(String id, String reason, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        if (!user.getAuthorities().contains(SysRole.ROLE_ADMIN)) {
            throw new ActivityException("只有超级管理员能终止协作请求");
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            return;
        }

        ActivityAction terminateActivityAction = new ActivityAction();
        terminateActivityAction.setActivity(activity);
        terminateActivityAction.setType(ActivityActionType.TERMINATE);
        terminateActivityAction.setCreatedBy(user);
        terminateActivityAction.setCreatedAt(new Date());

        activityActionRepository.save(terminateActivityAction);

        activity.setStatus(ActivityStatus.TERMINATED);
        activity.setModifiedAt(new Date());
        activityRepository.save(activity);

        List<Task> taskList = taskRepository.findByBizRefId(id);
        taskList.stream().forEach(message -> {
            if (message.getStatus() != TaskStatus.TERMINATED) {
                message.setStatus(TaskStatus.TERMINATED);
                message.setModifiedAt(new Date());
                taskRepository.save(message);
            }
        });
    }

    @Override
    public void deleteActivity(String id, String reason, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            return;
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (activity.getStatus() == ActivityStatus.IN_PROGRESS) {
            // 只能删除草稿,撤回状态的协作请求
            throw new ActivityException("不能删除流转中协作请求");
        }

        if (activity.getStatus() == ActivityStatus.REVOKED) {
            //delete related tasks & actions
            taskRepository.delete(taskRepository.findByBizRefId(activity.getId()));
            activityActionRepository.delete(activityActionRepository.findListByActivity(activity));
        }

        activityRepository.delete(activity);
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

//        Edms.getEdm("sms").dispatch(TaskNotifyRequest.TASK_NOTIFY, taskNotifyRequests);
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
//				messageNotifyRequest.setTelephone(receiver.getUser().getContactPhone());
//				messageNotifyRequest.setTaskId(message.getId());
//				messageNotifyRequest.setTaskSender(activityAction.getCreatedBy().getUsername());
//				messageNotifyRequest.setTaskTitle(activity.getTitle());
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
                    DefaultTaskNotifyRequest taskNotifyRequest = new DefaultTaskNotifyRequest();
                    taskNotifyRequest.setTelephone(toUser.getTelephone());
                    taskNotifyRequest.setTaskId(task.getId());
                    taskNotifyRequest.setTaskSender(activityAction.getCreatedBy().getUsername());
                    taskNotifyRequest.setTaskTitle(activity.getTitle());
                    taskNotifyRequests.add(taskNotifyRequest);
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

    private ActivityAction resolveActivityAction(String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            return null;
        }

        Task task = taskRepository.findById(taskId);
        if (task == null || StringUtils.isEmpty(task.getActionRefId())) {
            return null;
        }

        return activityActionRepository.findById(task.getActionRefId());
    }

}
