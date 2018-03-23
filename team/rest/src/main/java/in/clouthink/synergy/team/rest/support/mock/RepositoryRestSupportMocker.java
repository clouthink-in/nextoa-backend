package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.ActivityActionSearchParam;
import in.clouthink.synergy.team.rest.view.*;
import in.clouthink.synergy.team.rest.support.RepositoryRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryRestSupportMocker implements RepositoryRestSupport {

    @Override
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest) {
        return null;
    }

    @Override
    public ActivityDetailView getActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public List<String> getActivityAllowedActions(String id, User user) {
        return null;
    }

    @Override
    public Page<ActivityReadView> getActivityReadHistory(String id, ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityPrintView> getActivityPrintHistory(String id, ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityTransitionView> getActivityTransitionHistory(String id,
                                                                     ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityProcessView> getActivityProcessHistory(String id,
                                                               ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public List<ActivityProcessView> getActivityProcessHistory(String id) {
        return null;
    }

    @Override
    public Page<ActivityTaskView> getActivityTasks(String id, PageSearchParam queryRequest) {
        return null;
    }

    @Override
    public void terminateActivity(String id, User user) {

    }
}
