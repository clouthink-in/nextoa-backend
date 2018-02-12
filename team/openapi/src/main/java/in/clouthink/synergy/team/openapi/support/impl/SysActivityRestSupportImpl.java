package in.clouthink.synergy.team.openapi.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.support.SysActivityRestSupport;
import in.clouthink.synergy.team.service.MessageService;
import in.clouthink.synergy.team.service.ActivityService;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.openapi.dto.*;
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
public class SysActivityRestSupportImpl implements SysActivityRestSupport {

    @Autowired
    private ActivityService activityService;

//	@Autowired
//	private OrganizationService organizationService;

    @Autowired
    private MessageService messageService;

    @Override
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest) {
        queryRequest.setActivityStatus(null);
        Page<Activity> activityPage = activityService.listActivities(queryRequest);
        return new PageImpl<>(activityPage.getContent().stream().map(ActivitySummary::from).collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityPage.getTotalElements());
    }

    @Override
    public ActivityDetail getActivityDetail(String id, User user) {
        Activity activity = activityService.findActivityById(id, user);
        if (activity == null) {
            return null;
        }
        return ActivityDetail.from(activity);
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
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(String id, ActivityActionQueryParameter queryRequest) {
        queryRequest.setActivityActionTypes(ActivityActionType.START, ActivityActionType.REPLY, ActivityActionType.FORWARD);
        Page<ActivityAction> activityActionPage = activityService.getActivityActionHistory(id, queryRequest);
        return new PageImpl<>(activityActionPage.getContent()
                                             .stream()
                                             .map(ActivityTransitionSummary::from)
                                             .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              activityActionPage.getTotalElements());
    }

    @Override
    public Page<ActivityProcessSummary> getActivityProcessHistory(String id, ActivityActionQueryParameter queryRequest) {
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
    public List<ActivityProcessSummary> getActivityProcessHistory(String id) {
        ActivityActionQueryParameter queryRequest = new ActivityActionQueryParameter();
        queryRequest.setActivityActionTypes(ActivityActionType.REPLY, ActivityActionType.FORWARD);
        return activityService.getActivityActionHistoryList(id, queryRequest)
                              .stream()
                              .map(ActivityProcessSummary::from)
                              .collect(Collectors.toList());
    }

    @Override
    public Page<ActivityMessageSummary> getActivityMessages(String id, PageQueryParameter queryRequest) {
        Page<Task> messagePage = messageService.listActivityMessages(id, queryRequest);
        return new PageImpl<>(messagePage.getContent()
                                         .stream()
                                         .map(ActivityMessageSummary::from)
                                         .collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              messagePage.getTotalElements());
    }


    @Override
    public void terminateActivity(String id, User user) {
        activityService.terminateActivity(id, user);
    }

}
