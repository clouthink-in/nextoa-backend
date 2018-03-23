package in.clouthink.synergy.team.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.service.GroupService;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.ActivityActionSearchParam;
import in.clouthink.synergy.team.rest.support.RepositoryRestSupport;
import in.clouthink.synergy.team.service.TaskService;
import in.clouthink.synergy.team.service.ActivityService;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.rest.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositoryRestSupportImpl implements RepositoryRestSupport {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TaskService taskService;

    @Override
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest) {
        queryRequest.setActivityStatus(null);
        Page<Activity> activityPage = activityService.listActivities(queryRequest);
        return new PageImpl<>(activityPage.getContent()
                                          .stream()
                                          .map(ActivityView::from)
                                          .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public ActivityDetailView getActivityDetail(String id, User user) {
        Activity activity = activityService.findActivityById(id, user);
        if (activity == null) {
            return null;
        }
        return ActivityDetailView.from(activity);
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
    public List<ActivityProcessView> getActivityProcessHistory(String id) {
        ActivityActionSearchParam queryRequest = new ActivityActionSearchParam();
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        return activityService.getActivityActionHistoryList(id, queryRequest)
                              .stream()
                              .map(ActivityProcessView::from)
                              .collect(Collectors.toList());
    }

    @Override
    public Page<ActivityTaskView> getActivityTasks(String id, PageSearchParam queryRequest) {
        Page<Task> taskPage = taskService.listActiveTasks(id, queryRequest);
        return new PageImpl<>(taskPage.getContent()
                                      .stream()
                                      .map(ActivityTaskView::from)
                                      .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              taskPage.getTotalElements());
    }


    @Override
    public void terminateActivity(String id, User user) {
        activityService.terminateActivity(id, user);
    }

}
