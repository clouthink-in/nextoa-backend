package in.clouthink.synergy.team.service.impl;

import in.clouthink.daas.fss.spi.MutableFileObjectService;
import in.clouthink.synergy.account.domain.model.SysRole;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.exception.ActivityAttachmentException;
import in.clouthink.synergy.team.exception.ActivityException;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.*;
import in.clouthink.synergy.team.repository.*;
import in.clouthink.synergy.team.service.ActivityService;
import com.google.common.collect.Lists;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.spi.FileObjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Log logger = LogFactory.getLog(ActivityServiceImpl.class);

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityActionRepository activityActionRepository;

    @Autowired
    private ActivityTransitionRepository activityTransitionRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FavoriteMessageRepository favoriteMessageRepository;

    @Autowired
    private FileObjectService fileObjectService;

    @Override
    public Page<Activity> listActivities(ActivityQueryRequest request) {
        return activityRepository.queryPage(null, request, ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE);
    }

    @Override
    public Page<Activity> listActivities(ActivityQueryRequest request,
                                         ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                         User user) {
        return activityRepository.queryPage(user, request, includeOrExcludeStatus);
    }

    @Override
    public long countOfActivities(ActivityQueryRequest request,
                                  ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                  User user) {
        return activityRepository.queryCount(user, request, includeOrExcludeStatus);
    }

    @Override
    public Activity findActivityById(String id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity findActivityById(String id, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            return null;
        }

        if (activity.getStatus() == ActivityStatus.TERMINATED && !user.getAuthorities().contains(SysRole.ROLE_ADMIN)) {
            throw new ActivityException("该协作请求已终止,只有超级管理员能查看.");
        }

        if (user.getAuthorities().contains(SysRole.ROLE_ADMIN) || user.getAuthorities().contains(SysRole.ROLE_MGR)) {
            return activity;
        }
        if (activity.getCreatedBy().getId().equals(user.getId())) {
            return activity;
        }

        Task task = messageRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null) {
            return activity;
        }

        throw new ActivityException("无查看该协作请求的权限");
    }

    @Override
    public Activity createActivity(SaveActivityRequest request, User user) {
        checkUser(user);
        checkSaveActivityRequest(request);

        Activity activity = new Activity();
        activity.setTitle(request.getTitle());
        activity.setType(request.getType());
        activity.setCategory(request.getCategory());
        activity.setUrgent(request.getUrgent());
        activity.setContent(request.getContent());
        activity.setCreatedAt(new Date());
        activity.setCreatedBy(user);
        activity.setStatus(ActivityStatus.DRAFT);
        activity.setVersion(2);
        return activityRepository.save(activity);
    }

    @Override
    public Activity copyActivity(String id, User user) {
        Activity existedActivity = activityRepository.findById(id);
        if (existedActivity == null) {
            throw new ActivityNotFoundException(id);
        }
        if (existedActivity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (!Activity.isCopyAllowed(existedActivity)) {
            throw new ActivityException("该协作请求禁止再处理.");
        }

        Activity activity = new Activity();
        activity.setTitle(existedActivity.getTitle());
        activity.setType(existedActivity.getType());
        activity.setCategory(existedActivity.getCategory());
        activity.setUrgent(existedActivity.isUrgent());
        activity.setContent(existedActivity.getContent());
        activity.setCreatedAt(new Date());
        activity.setCreatedBy(user);
        activity.setStatus(ActivityStatus.DRAFT);
        activity.setVersion(2);
        return activityRepository.save(activity);
    }

    @Override
    public void updateActivity(String id, SaveActivityRequest request, User user) {
        checkUser(user);
        checkSaveActivityRequest(request);

        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        if (activity.getStatus() == ActivityStatus.IN_PROGRESS) {
            if (!activity.getCreatedBy().getId().equals(user.getId())) {
                //流转过程中,且协作请求禁止编辑
                if (activity.getAllowedActions() == null || !activity.getAllowedActions().contains(ActivityActionType.EDIT)) {
                    throw new ActivityException("不能修改非草稿或非撤回状态的协作请求");
                }
            }
        }

        activity.setType(request.getType());
        activity.setContent(request.getContent());
        activity.setUrgent(request.getUrgent());
        activity.setTitle(request.getTitle());
        activity.setCategory(request.getCategory());
        activity.setModifiedAt(new Date());
        activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(String id, User user) {
        checkUser(user);
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            return;
        }
        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        // 只能删除草稿,撤回状态的协作请求
        if (activity.getStatus() == ActivityStatus.IN_PROGRESS) {
            throw new ActivityException("不能删除流转中协作请求");
        }

        if (activity.getStatus() == ActivityStatus.REVOKED) {
            List<Task> taskList = messageRepository.findByBizRefId(activity.getId());
            messageRepository.delete(taskList);
        }

        activityRepository.delete(activity);
    }

    @Override
    public void revokeActivity(String id, User user) {
        checkUser(user);
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

        List<Task> taskList = messageRepository.findByBizRefId(activity.getId());
        for (Task task : taskList) {
            if (!task.getReceiver().getId().equalsIgnoreCase(activity.getCreatedBy().getId()) &&
                    task.getStatus() == TaskStatus.PROCESSED) {
                throw new ActivityException("协作请求已被处理,不能进行撤回操作.");
            }
        }

        ActivityAction activityAction = new ActivityAction();
        activityAction.setCreatedAt(new Date());
        activityAction.setCreatedBy(user);
        activityAction.setActivity(activity);
        activityAction.setType(ActivityActionType.REVOKE);

        Edms.getEdm("activity").dispatch(ActivityAction.REVOKE_ACTION, activityAction);
    }

    @Override
    public void startActivity(String id, StartActivityRequest request, User user) {
        checkUser(user);
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        checkSavedActivity(activity);
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

        Edms.getEdm("activity").dispatch(ActivityAction.START_ACTION, startActivityAction);
    }

    @Override
    public void printActivity(String id, User user) {
        checkUser(user);
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        if (activity.getStatus() == ActivityStatus.TERMINATED) {
            throw new ActivityException("该协作请求已终止,禁止操作.");
        }

        List<ActivityActionType> allowedActions = activity.getAllowedActions();
        if (allowedActions == null || !allowedActions.contains(ActivityActionType.PRINT)) {
            throw new ActivityException("该协作请求禁止打印.");
        }
        ActivityAction printActivityAction = new ActivityAction();
        printActivityAction.setActivity(activity);
        printActivityAction.setType(ActivityActionType.PRINT);
        printActivityAction.setCreatedBy(user);
        printActivityAction.setCreatedAt(new Date());
        activityActionRepository.save(printActivityAction);
    }

    @Override
    public void replyActivity(String id, ReplyActivityRequest request, User user) {
        checkUser(user);

        Task task = messageRepository.findByBizRefIdAndReceiver(id, user);
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

        Edms.getEdm("activity")
            .dispatch(ActivityAction.REPLY_ACTION, new ActivityTransitionRequest(previousActivityAction,
                                                                                 replyActivityAction));
    }

    @Override
    public void forwardActivity(String id, ForwardActivityRequest request, User user) {
        checkUser(user);

        Task task = messageRepository.findByBizRefIdAndReceiver(id, user);
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

        Edms.getEdm("activity")
            .dispatch(ActivityAction.FORWARD_ACTION, new ActivityTransitionRequest(previousActivityAction,
                                                                                   forwardActivityAction));
    }

    @Override
    public void endActivity(String id, User user) {
        checkUser(user);

        Task task = messageRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null && task.getStatus() == TaskStatus.PROCESSED) {
            throw new ActivityException("该协作请求任务已经处理过了,不能重复处理.");
        }

        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        endActivity(activity, user);
    }

    @Override
    public void endActivity(Activity activity, User user) {
        checkUser(user);
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

        Edms.getEdm("activity").dispatch(ActivityAction.END_ACTION, endActivityAction);
    }

    @Override
    public void terminateActivity(String id, User user) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        terminateActivity(activity, user);
    }

    @Override
    public void terminateActivity(Activity activity, User user) {
        if (!user.getAuthorities().contains(SysRole.ROLE_ADMIN)) {
            throw new ActivityException("只有超级管理员能终止协作请求");
        }

        ActivityAction terminateActivityAction = new ActivityAction();
        terminateActivityAction.setActivity(activity);
        terminateActivityAction.setType(ActivityActionType.TERMINATE);
        terminateActivityAction.setCreatedBy(user);
        terminateActivityAction.setCreatedAt(new Date());

        Edms.getEdm("activity").dispatch(ActivityAction.TERMINATE_ACTION, terminateActivityAction);
    }

    @Override
    public ActivityAction findActivityActionById(String id) {
        return activityActionRepository.findById(id);
    }

    @Override
    public Page<ActivityAction> getActivityActionHistory(String id, ActivityActionQueryRequest request) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        return activityActionRepository.queryPage(activity, request);
    }

    @Override
    @Deprecated
    public List<ActivityAction> getActivityActionHistoryList(String id, ActivityActionQueryRequest queryRequest) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        return activityActionRepository.queryList(activity, queryRequest);
    }

    @Override
    public List<ActivityAction> getActivityProcessHistoryList(String id, User user) {
        //Caution: to improve the performance , the activity transition id equals the activity action id whatever it's current, previous or next
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }
        String currentUserId = user.getId();
        List<ActivityTransition> activityTransitions = activityTransitionRepository.findListByActivityOrderByCreatedAtDesc(activity);
        List<String> matchedActivityActionIds = new ArrayList<>();
        Map<String, ActivityTransition> activityTransitionMap = new HashMap<>();
        activityTransitions.stream()
                           .forEach(activityTransition -> activityTransitionMap.put(activityTransition.getId(), activityTransition));
        activityTransitions.stream().forEach(activityTransition -> {
            //won't re-process the transition to improve the performance
            if (activityTransition.isProcessed()) {
                return;
            }

            //back-ward to get the previous activity transition
            ActivityTransition activityTransTmp4Prev = null;
            String prevActonIdTmp = activityTransition.getPreviousActionId();
            if (!StringUtils.isEmpty(prevActonIdTmp)) {
                activityTransTmp4Prev = activityTransitionMap.get(prevActonIdTmp);
            }

            //add to result if matched the criteria
            if (activityTransition.getParticipantIds().contains(currentUserId) ||
                    currentUserId.equalsIgnoreCase(activityTransition.getCreatorId()) ||
                    (activityTransTmp4Prev != null && currentUserId.equalsIgnoreCase(activityTransTmp4Prev.getCreatorId()))) {
                if (activityTransition.getActivityActionType() != ActivityActionType.START) {
                    matchedActivityActionIds.add(activityTransition.getCurrentActionId());
                }

                //handle the link in backward direction
                ActivityTransition activityTransitionIterator = activityTransition;
                while (activityTransitionIterator.getPreviousActionId() != null) {
                    String previousActionId = activityTransitionIterator.getPreviousActionId();
                    ActivityTransition previousActivityTransition = activityTransitionMap.get(previousActionId);

                    //update the iterator to previous activity transition
                    activityTransitionIterator = previousActivityTransition;

                    // if the previous transition is processed, continue the backward logic
                    if (previousActivityTransition.isProcessed()) {
                        continue;
                    }

                    //add to result because it's on the backward branch (which is matched at the leaf node)
                    if (previousActivityTransition.getActivityActionType() != ActivityActionType.START) {
                        matchedActivityActionIds.add(previousActivityTransition.getCurrentActionId());
                    }
                    previousActivityTransition.setProcessed(true);
                }
            }
            activityTransition.setProcessed(true);
        });

        return activityActionRepository.findListByIdInOrderByCreatedAtDesc(matchedActivityActionIds.toArray(new String[]{}));
    }

    @Override
    public boolean isRead(Activity activity, User user) {
        return activityActionRepository.findFirstByActivityAndTypeAndCreatedBy(activity, ActivityActionType.READ, user) != null;
    }

    @Override
    public boolean isFavorite(Activity activity, User user) {
        Task task = messageRepository.findByBizRefIdAndReceiver(activity.getId(), user);
        if (task == null) {
            return false;
        }

        return favoriteMessageRepository.findByMessageAndCreatedBy(task, user) != null;
    }

    @Override
    public void deleteActivityAttachment(String activityId, String fileId, User user) {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            throw new ActivityNotFoundException(activityId);
        }

        if (activity.getStatus() == null ||
                activity.getStatus() == ActivityStatus.DRAFT ||
                activity.getStatus() == ActivityStatus.REVOKED) {
            FileObject fileObject = fileObjectService.findById(fileId);
            String uploadedBy = fileObject.getUploadedBy();
            if (!user.getUsername().equals(uploadedBy) &&
                    !user.getUsername().equals(activity.getCreatedBy().getUsername())) {
                throw new ActivityAttachmentException("只能删除自己上传的附件.");
            }
            try {

                ((MutableFileObjectService) fileObjectService).deleteById(fileId);
            } catch (Exception e) {
            }
            return;
        }

        if (activity.getStatus() == ActivityStatus.IN_PROGRESS) {
            if (!Activity.isEditAllowed(activity)) {
                throw new ActivityAttachmentException("该协作请求禁止编辑,不能删除附件.");
            }
            try {
                ((MutableFileObjectService) fileObjectService).deleteById(fileId);
            } catch (Exception e) {
            }
            return;
        }

        try {
            logger.warn(String.format("User[username=%s] delete Activity[id=%s,status=%]#File[id=%s]",
                                      user.getUsername(),
                                      activityId,
                                      activity.getStatus(),
                                      fileId));
            ((MutableFileObjectService) fileObjectService).deleteById(fileId);
        } catch (Exception e) {
        }
    }

    @Override
    public void markActivityBusinessComplete(Activity activity) {
        if (activity == null) {
            return;
        }
        activityRepository.markActivityBusinessComplete(activity);
    }

    private void checkSaveActivityRequest(SaveActivityRequest request) {
        // 要求刚进入时可以自动保存,去掉验证
        // if (StringUtils.isEmpty(request.getTitle())) {
        // throw new ActivityException("标题不能为空");
        // }
        //
        // if (StringUtils.isEmpty(request.getContent())) {
        // throw new ActivityException("内容不能为空");
        // }
    }

    private void checkSavedActivity(Activity activity) {
        if (StringUtils.isEmpty(activity.getTitle())) {
            throw new ActivityException("标题不能为空");
        }

        if (StringUtils.isEmpty(activity.getContent())) {
            throw new ActivityException("内容不能为空");
        }
    }

    private void checkUser(User user) {
//        if (user.getUserType() == UserType.SYSUSER) {
//            throw new ActivityException("系统用户不能操作协作请求.");
//        }
    }

    private ActivityAction resolveActivityAction(String messageId) {
        if (StringUtils.isEmpty(messageId)) {
            return null;
        }

        Task task = messageRepository.findById(messageId);
        if (task == null || StringUtils.isEmpty(task.getActionRefId())) {
            return null;
        }

        return activityActionRepository.findById(task.getActionRefId());
    }

}
