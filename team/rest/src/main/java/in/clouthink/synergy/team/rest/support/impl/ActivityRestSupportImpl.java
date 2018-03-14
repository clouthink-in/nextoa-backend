package in.clouthink.synergy.team.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.ActivityQueryRequest;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import in.clouthink.synergy.team.rest.support.ReceiverBuilder;
import in.clouthink.synergy.team.service.TaskService;
import in.clouthink.synergy.team.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ActivityRestSupportImpl implements ActivityRestSupport, ReceiverBuilder {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AccountService accountService;

//	@Autowired
//	private OrganizationService organizationService;

    @Override
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.TERMINATED);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivityQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivitySummary> listDraftActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.DRAFT);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivitySummary> listProcessingActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.IN_PROGRESS);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivitySummary> listRevokedActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.REVOKED);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public long countOfAllActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.TERMINATED);
        return activityService.countOfActivities(queryRequest,
                                                 ActivityQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
                                                 user);
    }

    @Override
    public long countOfDraftActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.DRAFT);
        return activityService.countOfActivities(queryRequest,
                                                 ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public long countOfProcessingActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.IN_PROGRESS);
        return activityService.countOfActivities(queryRequest,
                                                 ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public long countOfRevokedActivities(ActivityQueryParameter queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.REVOKED);
        return activityService.countOfActivities(queryRequest,
                                                 ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public ActivityDetail getActivityDetail(String id, User user) {
        Activity activity = activityService.findActivityById(id, user);
        if (activity == null) {
            return null;
        }
        ActivityDetail result = ActivityDetail.from(activity);
        result.setFavorite(activityService.isFavorite(activity, user));
        result.setRead(activityService.isRead(activity, user));
        return result;
    }

    @Override
    public ActivityDetail copyActivityDetail(String id, User user) {
        return ActivityDetail.from(activityService.copyActivity(id, user));
    }

    @Override
    public List<String> getActivityAllowedActions(String id, User user) {
        Activity activity = activityService.findActivityById(id, user);
        if (activity.getAllowedActions() == null) {
            return Collections.emptyList();
        }
        return activity.getAllowedActions().stream().map(actionType -> actionType.name()).collect(Collectors.toList());
    }

    @Override
    public String createActivity(SaveActivityParameter request, User user) {
        Activity activity = activityService.createActivity(request, user);
        return activity.getId();
    }

    @Override
    public void updateActivity(String id, SaveActivityParameter request, User user) {
        activityService.updateActivity(id, request, user);
    }

    @Override
    public void deleteActivity(String id, User user) {
        activityService.deleteActivity(id, user);
    }

    @Override
    public void revokeActivity(String id, User user) {
        activityService.revokeActivity(id, user);
    }

    @Override
    public void startActivity(String id, StartActivityParameter parameter, User user) {
        StartActivityRequestParameter startRequest = new StartActivityRequestParameter();
        startRequest.setDisabledActions(parameter.getDisabledActions());
        startRequest.setToReceivers(buildReceivers(parameter.getTo()));
        startRequest.setCcReceivers(buildReceivers(parameter.getCc()));

        activityService.startActivity(id, startRequest, user);
    }

    @Override
    public void replyActivity(String id, ReplyActivityParameter parameter, User user) {
        ActivityActionRequestParameter request = new ActivityActionRequestParameter();
        request.setMessageId(parameter.getMessageId());
        request.setContent(parameter.getContent());
        request.setActivityContent(parameter.getActivityContent());
        request.setToReceivers(buildReceivers(parameter.getTo()));
        request.setCcReceivers(buildReceivers(parameter.getCc()));


        activityService.replyActivity(id, request, user);
    }

    @Override
    public void forwardActivity(String id, ForwardActivityParameter parameter, User user) {
        ActivityActionRequestParameter request = new ActivityActionRequestParameter();
        request.setMessageId(parameter.getMessageId());
        request.setContent(parameter.getContent());
        request.setActivityContent(parameter.getActivityContent());
        request.setToReceivers(buildReceivers(parameter.getTo()));
        request.setCcReceivers(buildReceivers(parameter.getCc()));

        activityService.forwardActivity(id, request, user);
    }

    @Override
    public void printActivity(String id, User user) {
        activityService.printActivity(id, user);
    }

    public void markActivityAsRead(String id, User user) {
        activityService.markActivityAsRead(id, user);
    }

    @Override
    public void markActivityAsDone(String id, User user) {
        activityService.endActivity(id, user);
    }

    @Override
    public Page<ActivityReadSummary> getActivityReadHistory(String id, ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.READ);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityReadSummary::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityPrintSummary> getActivityPrintHistory(String id, ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.PRINT);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityPrintSummary::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(String id,
                                                                        ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.START,
                                            ActivityActionType.REPLY,
                                            ActivityActionType.FORWARD);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityTransitionSummary::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityTransitionSummary> getActivityEndHistory(String id, ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.END);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityTransitionSummary::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityProcessSummary> getActivityProcessHistory(String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityProcessSummary::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public List<ActivityProcessSummary> getActivityProcessHistory(String id, User byWho) {
        Activity activity = activityService.findActivityById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(id);
        }

        //@since 2017.8.18 参与人能看到所有的处理意见, 不再过滤权限树（就是参与过的树上的节点才能看到）, 因此屏蔽掉以前的处理逻辑
        //		if (activity.getVersion() == 1 || activity.getVersion() == 0) {
        //			ActivityActionQueryParameter queryRequest = new ActivityActionQueryParameter();
        //			queryRequest.setStart(0);
        //			queryRequest.setLimit(100);
        //			queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        //			return activityService.getActivityActionHistoryList(id, queryRequest)
        //							   .stream()
        //							   .map(ActivityProcessSummary::from)
        //							   .collect(Collectors.toList());
        //		}
        //		else {
        //			return activityService.getActivityProcessHistoryList(id, byWho)
        //							   .stream()
        //							   .map(ActivityProcessSummary::from)
        //							   .collect(Collectors.toList());
        //
        //		}
        ActivityActionQueryParameter queryRequest = new ActivityActionQueryParameter();
        queryRequest.setStart(0);
        queryRequest.setLimit(100);
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        return activityService.getActivityActionHistoryList(id, queryRequest)
                              .stream()
                              .map(ActivityProcessSummary::from)
                              .collect(Collectors.toList());
    }

    @Override
    public Receiver buildReceiver(ReceiverParameter receiverParameter) {
        Receiver receiver = new Receiver();
        receiver.setUser(accountService.findById(receiverParameter.getUserId()));
        receiver.setNotifyEnabled(receiverParameter.isNotify());
        return receiver;
    }

    @Override
    public List<Receiver> buildReceivers(List<ReceiverParameter> parameters) {
        return parameters.stream()
                         .map(receiverParameter -> buildReceiver(receiverParameter))
                         .collect(Collectors.toList());
    }

    @Override
    public Page<ActivityTaskSummary> getActivityMessages(String id, PageQueryParameter queryRequest) {
        Page<Task> messagePage = taskService.listActiveTasks(id, queryRequest);
        return new PageImpl<>(messagePage.getContent()
                                         .stream()
                                         .map(ActivityTaskSummary::from)
                                         .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              messagePage.getTotalElements());
    }

    private ActivitySummary convertToActivitySummary(Activity activity, User user) {
        ActivitySummary result = ActivitySummary.from(activity);
        result.setFavorite(activityService.isFavorite(activity, user));
        if (activity.getStatus() == ActivityStatus.REVOKED || activity.getStatus() == ActivityStatus.DRAFT) {
            result.setRead(true);
        }
        else {
            result.setRead(activityService.isRead(activity, user));
        }
        return result;
    }


}
