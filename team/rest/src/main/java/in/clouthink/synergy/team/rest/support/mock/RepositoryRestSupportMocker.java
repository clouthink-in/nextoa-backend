package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.RepositoryRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryRestSupportMocker implements RepositoryRestSupport {

    @Override
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest) {
        return null;
    }

    @Override
    public ActivityDetail getActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public List<String> getActivityAllowedActions(String id, User user) {
        return null;
    }

    @Override
    public Page<ActivityReadSummary> getActivityReadHistory(String id, ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityPrintSummary> getActivityPrintHistory(String id, ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(String id,
                                                                        ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityProcessSummary> getActivityProcessHistory(String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public List<ActivityProcessSummary> getActivityProcessHistory(String id) {
        return null;
    }

    @Override
    public Page<ActivityTaskSummary> getActivityTasks(String id, PageQueryParameter queryRequest) {
        return null;
    }

    @Override
    public void terminateActivity(String id, User user) {

    }
}
