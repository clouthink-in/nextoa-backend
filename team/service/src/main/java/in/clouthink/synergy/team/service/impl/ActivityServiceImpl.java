package in.clouthink.synergy.team.service.impl;

import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.*;
import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;
import in.clouthink.synergy.team.domain.request.StartActivityRequest;
import in.clouthink.synergy.team.engine.actor.*;
import in.clouthink.synergy.team.engine.service.TeamEngine;
import in.clouthink.synergy.team.exception.ActivityException;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.exception.EngineException;
import in.clouthink.synergy.team.repository.*;
import in.clouthink.synergy.team.service.ActivityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Log logger = LogFactory.getLog(ActivityServiceImpl.class);

    @Value("${team.queue.response.timeout:1000}")
    private int responseTimeout;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityActionRepository activityActionRepository;

    @Autowired
    private ActivityTransitionRepository activityTransitionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FavoriteTaskRepository favoriteTaskRepository;

    @Autowired
    private TeamEngine teamEngine;

    @Override
    public Page<Activity> listActivities(ActivitySearchRequest request) {
        return activityRepository.queryPage(null, request, ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE);
    }

    @Override
    public Page<Activity> listActivities(ActivitySearchRequest request,
                                         ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                         User user) {
        return activityRepository.queryPage(user, request, includeOrExcludeStatus);
    }

    @Override
    public long countOfActivities(ActivitySearchRequest request,
                                  ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
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

        if (activity.getStatus() == ActivityStatus.TERMINATED && !user.getAuthorities().contains(Roles.ROLE_ADMIN)) {
            throw new ActivityException("该协作请求已终止,只有超级管理员能查看.");
        }

        if (user.getAuthorities().contains(Roles.ROLE_ADMIN) || user.getAuthorities().contains(Roles.ROLE_MGR)) {
            return activity;
        }
        if (activity.getCreatedBy().getId().equals(user.getId())) {
            return activity;
        }

        Task task = taskRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null) {
            return activity;
        }

        throw new ActivityException("无查看该协作请求的权限");
    }

    @Override
    public ActivityAction findActivityActionById(String id) {
        return activityActionRepository.findById(id);
    }

    @Override
    public Page<ActivityAction> getActivityActionHistory(String id, ActivityActionSearchRequest request) {
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        return activityActionRepository.queryPage(activity, request);
    }

    @Override
    @Deprecated
    public List<ActivityAction> getActivityActionHistoryList(String id, ActivityActionSearchRequest queryRequest) {
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
        List<ActivityTransition> activityTransitions = activityTransitionRepository.findListByActivityOrderByCreatedAtDesc(
                activity);
        List<String> matchedActivityActionIds = new ArrayList<>();
        Map<String, ActivityTransition> activityTransitionMap = new HashMap<>();
        activityTransitions.stream()
                           .forEach(activityTransition -> activityTransitionMap.put(activityTransition.getId(),
                                                                                    activityTransition));
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
                    (activityTransTmp4Prev != null &&
                            currentUserId.equalsIgnoreCase(activityTransTmp4Prev.getCreatorId()))) {
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
        return activityActionRepository.findFirstByActivityAndTypeAndCreatedBy(activity,
                                                                               ActivityActionType.READ,
                                                                               user) != null;
    }

    @Override
    public boolean isFavorite(Activity activity, User user) {
        Task task = taskRepository.findByBizRefIdAndReceiver(activity.getId(), user);
        if (task == null) {
            return false;
        }

        return favoriteTaskRepository.findByTaskAndCreatedBy(task, user) != null;
    }

    @Override
    public Activity createActivity(SaveActivityRequest request, User user) {
        Activity activity = new Activity();
        activity.setTitle(request.getTitle());
        activity.setType(request.getType());
        activity.setCategory(request.getCategory());
        activity.setUrgent(request.getUrgent());
        activity.setContent(request.getContent());
        activity.setCreatedAt(new Date());
        activity.setCreatedBy(user);
        activity.setStatus(ActivityStatus.DRAFT);

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
            throw new ActivityException("该协作请求禁止复制.");
        }

        //now do copy
        //now do async to sync
        try {
            CopyActivityResponse response = teamEngine.copyActivity(id, user)
                                                      .exceptionally(ex -> new CopyActivityResponse(ex))
                                                      .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
            return response.getActivity();
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Copy activity failed.", e);
        }
    }

    @Override
    public void updateActivity(String id, SaveActivityRequest request, User user) {
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
            if (!activity.getCreatedBy().getId().equals(user.getId())) {
                //流转过程中,且协作请求禁止编辑
                if (activity.getAllowedActions() == null ||
                        !activity.getAllowedActions().contains(ActivityActionType.EDIT)) {
                    throw new ActivityException("不能修改非草稿或非撤回状态的协作请求");
                }
            }
        }

        //now do update
        //now do async to sync
        try {
            UpdateActivityResponse response = teamEngine.updateActivity(id, request, user)
                                                        .exceptionally(ex -> new UpdateActivityResponse(ex))
                                                        .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Update activity failed.", e);
        }
    }

    @Override
    public void deleteActivity(String id, User user) {
        //fail-fast: pre-check
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

        //now do delete
        //now do async to sync
        try {
            DeleteActivityResponse response = teamEngine.deleteActivity(id, null, user)
                                                        .exceptionally(ex -> new DeleteActivityResponse(ex))
                                                        .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Delete activity failed.", e);
        }
    }

    @Override
    public void revokeActivity(String id, User user) {
        //fail-fast: pre-check
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

        //now do revoke
        //now do async to sync
        try {
            RevokeActivityResponse response = teamEngine.revokeActivity(id, null, user)
                                                        .exceptionally(ex -> new RevokeActivityResponse(ex))
                                                        .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Revoke activity failed.", e);
        }
    }

    @Override
    public void startActivity(String id, StartActivityRequest request, User user) {
        //fail-fast: pre-check
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

        //now do start
        //now do async to sync
        try {
            StartActivityResponse response = teamEngine.startActivity(id, request, user)
                                                       .exceptionally(ex -> new StartActivityResponse(ex))
                                                       .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Start activity failed.", e);
        }
    }

    @Override
    public void replyActivity(String id, ReplyActivityRequest request, User user) {
        //fail-fast: pre-check
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

        //now do reply
        //now do async to sync
        try {
            ReplyActivityResponse response = teamEngine.replyActivity(id, request, user)
                                                       .exceptionally(ex -> new ReplyActivityResponse(ex))
                                                       .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Reply activity failed.", e);
        }
    }

    @Override
    public void forwardActivity(String id, ForwardActivityRequest request, User user) {
        //fail-fast: pre-check
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

        //now do forward
        //now do async to sync
        try {
            ForwardActivityResponse response = teamEngine.forwardActivity(id, request, user)
                                                         .exceptionally(ex -> new ForwardActivityResponse(ex))
                                                         .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Forward activity failed.", e);
        }
    }

    @Override
    public void endActivity(String id, User user) {
        //fail-fast: pre-check
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        Task task = taskRepository.findByBizRefIdAndReceiver(id, user);
        if (task != null && task.getStatus() == TaskStatus.PROCESSED) {
            throw new ActivityException("该协作请求任务已经处理过了,不能重复处理.");
        }

        //now do end
        //now do async to sync
        try {
            EndActivityResponse response = teamEngine.endActivity(id, null, user)
                                                     .exceptionally(ex -> new EndActivityResponse(ex))
                                                     .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("End activity failed.", e);
        }
    }

    @Override
    public void terminateActivity(String id, User user) {
        //fail-fast: pre-check
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        if (!user.getAuthorities().contains(Roles.ROLE_ADMIN)) {
            throw new ActivityException("只有超级管理员能终止协作请求");
        }

        //now do terminate
        //now do async to sync
        try {
            TerminateActivityResponse response = teamEngine.terminateActivity(id, null, user)
                                                           .exceptionally(ex -> new TerminateActivityResponse(ex))
                                                           .get(responseTimeout, TimeUnit.MILLISECONDS);
            if (response.hasError()) {
                response.throwOut();
            }
        } catch (EngineException e) {
            throw e;
        } catch (Throwable e) {
            throw new ActivityException("Terminate activity failed.", e);
        }
    }

    @Override
    public void printActivity(String id, User user) {
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
    public void markActivityAsRead(String id, User user) {
        //fail-fast: pre-check
        Activity activity = activityRepository.findById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        //now do read
        teamEngine.markActivityAsRead(id, user);
    }

}
