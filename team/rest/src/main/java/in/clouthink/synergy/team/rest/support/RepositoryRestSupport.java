package in.clouthink.synergy.team.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.ActivityActionSearchParam;
import in.clouthink.synergy.team.rest.view.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface RepositoryRestSupport {

    Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest);

    ActivityDetailView getActivityDetail(String id, User user);

    List<String> getActivityAllowedActions(String id, User user);

    Page<ActivityReadView> getActivityReadHistory(String id, ActivityActionSearchParam queryRequest);

    Page<ActivityPrintView> getActivityPrintHistory(String id, ActivityActionSearchParam queryRequest);

    Page<ActivityTransitionView> getActivityTransitionHistory(String id, ActivityActionSearchParam queryRequest);

    Page<ActivityProcessView> getActivityProcessHistory(String id, ActivityActionSearchParam queryRequest);

    List<ActivityProcessView> getActivityProcessHistory(String id);

    Page<ActivityTaskView> getActivityTasks(String id, PageSearchParam queryRequest);

    void terminateActivity(String id, User user);

}
