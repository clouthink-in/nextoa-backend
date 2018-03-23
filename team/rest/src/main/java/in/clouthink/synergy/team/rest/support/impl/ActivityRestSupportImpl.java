package in.clouthink.synergy.team.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.GroupService;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.ActivitySearchRequest;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.rest.param.*;
import in.clouthink.synergy.team.rest.view.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import in.clouthink.synergy.team.rest.support.ReceiverBuilder;
import in.clouthink.synergy.team.service.TaskService;
import in.clouthink.synergy.team.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityRestSupportImpl implements ActivityRestSupport, ReceiverBuilder {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AccountService accountService;

	@Autowired
	private GroupService groupService;

    @Override
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.TERMINATED);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivitySearchRequest.IncludeOrExcludeStatus.EXCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivityView> listDraftActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.DRAFT);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivityView> listProcessingActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.IN_PROGRESS);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public Page<ActivityView> listRevokedActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.REVOKED);
        Page<Activity> activityPage = activityService.listActivities(queryRequest,
                                                                     ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                                     user);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(activity -> convertToActivitySummary(activity, user))
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public long countOfAllActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.TERMINATED);
        return activityService.countOfActivities(queryRequest,
                                                 ActivitySearchRequest.IncludeOrExcludeStatus.EXCLUDE,
                                                 user);
    }

    @Override
    public long countOfDraftActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.DRAFT);
        return activityService.countOfActivities(queryRequest,
                                                 ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public long countOfProcessingActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.IN_PROGRESS);
        return activityService.countOfActivities(queryRequest,
                                                 ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public long countOfRevokedActivities(ActivitySearchParam queryRequest, User user) {
        queryRequest.setActivityStatus(ActivityStatus.REVOKED);
        return activityService.countOfActivities(queryRequest,
                                                 ActivitySearchRequest.IncludeOrExcludeStatus.INCLUDE,
                                                 user);
    }

    @Override
    public ActivityDetailView getActivityDetail(String id, User user) {
        Activity activity = activityService.findActivityById(id, user);
        if (activity == null) {
            return null;
        }
        ActivityDetailView result = ActivityDetailView.from(activity);
        result.setFavorite(activityService.isFavorite(activity, user));
        result.setRead(activityService.isRead(activity, user));
        return result;
    }

    @Override
    public ActivityDetailView copyActivityDetail(String id, User user) {
        return ActivityDetailView.from(activityService.copyActivity(id, user));
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
    public String createActivity(SaveActivityParam request, User user) {
        Activity activity = activityService.createActivity(request, user);
        return activity.getId();
    }

    @Override
    public void updateActivity(String id, SaveActivityParam request, User user) {
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
    public void startActivity(String id, StartActivityParam parameter, User user) {
        StartActivityRequestParam startRequest = new StartActivityRequestParam();
        startRequest.setDisabledActions(parameter.getDisabledActions());
        startRequest.setToReceivers(buildReceivers(parameter.getTo()));
        startRequest.setCcReceivers(buildReceivers(parameter.getCc()));

        activityService.startActivity(id, startRequest, user);
    }

    @Override
    public void replyActivity(String id, ReplyActivityParam parameter, User user) {
        ActivityActionRequestParam request = new ActivityActionRequestParam();
        request.setTaskId(parameter.getTaskId());
        request.setContent(parameter.getComment());
        request.setActivityContent(parameter.getActivityContent());
        request.setToReceivers(buildReceivers(parameter.getTo()));
        request.setCcReceivers(buildReceivers(parameter.getCc()));


        activityService.replyActivity(id, request, user);
    }

    @Override
    public void forwardActivity(String id, ForwardActivityParam parameter, User user) {
        ActivityActionRequestParam request = new ActivityActionRequestParam();
        request.setTaskId(parameter.getTaskId());
        request.setContent(parameter.getComment());
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
    public Page<ActivityReadView> getActivityReadHistory(String id, ActivityActionSearchParam queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.READ);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityReadView::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityPrintView> getActivityPrintHistory(String id, ActivityActionSearchParam queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.PRINT);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityPrintView::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityTransitionView> getActivityTransitionHistory(String id,
                                                                     ActivityActionSearchParam queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.START,
                                            ActivityActionType.REPLY,
                                            ActivityActionType.FORWARD);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityTransitionView::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityTransitionView> getActivityEndHistory(String id, ActivityActionSearchParam queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.END);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityTransitionView::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityProcessView> getActivityProcessHistory(String id,
                                                               ActivityActionSearchParam queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                                .stream()
                                                .map(ActivityProcessView::from)
                                                .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public List<ActivityProcessView> getActivityProcessHistory(String id, User byWho) {
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
        ActivityActionSearchParam queryRequest = new ActivityActionSearchParam();
        queryRequest.setStart(0);
        queryRequest.setLimit(100);
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        return activityService.getActivityActionHistoryList(id, queryRequest)
                              .stream()
                              .map(ActivityProcessView::from)
                              .collect(Collectors.toList());
    }

    @Override
    public Receiver buildReceiver(ReceiverParam receiverParam) {
        Receiver receiver = new Receiver();
        receiver.setUser(accountService.findById(receiverParam.getUserId()));
        receiver.setNotifyEnabled(receiverParam.isNotify());
        return receiver;
    }

    @Override
    public List<Receiver> buildReceivers(List<ReceiverParam> parameters) {
        return parameters.stream()
                         .map(receiverParameter -> buildReceiver(receiverParameter))
                         .collect(Collectors.toList());
    }

    @Override
    public Page<ActivityTaskView> getActivityMessages(String id, PageSearchParam queryRequest) {
        Page<Task> messagePage = taskService.listActiveTasks(id, queryRequest);
        return new PageImpl<>(messagePage.getContent()
                                         .stream()
                                         .map(ActivityTaskView::from)
                                         .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              messagePage.getTotalElements());
    }

    private ActivityView convertToActivitySummary(Activity activity, User user) {
        ActivityView result = ActivityView.from(activity);
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
