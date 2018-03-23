package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.rest.param.*;
import in.clouthink.synergy.team.rest.view.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import in.clouthink.synergy.team.rest.support.ReceiverBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityRestSupportMocker implements ActivityRestSupport, ReceiverBuilder {

    @Override
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivityView> listDraftActivities(ActivitySearchParam queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivityView> listProcessingActivities(ActivitySearchParam queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivityView> listRevokedActivities(ActivitySearchParam queryRequest, User user) {
        return null;
    }

    @Override
    public long countOfAllActivities(ActivitySearchParam queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfDraftActivities(ActivitySearchParam queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfProcessingActivities(ActivitySearchParam queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfRevokedActivities(ActivitySearchParam queryRequest, User user) {
        return 0;
    }

    @Override
    public ActivityDetailView getActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public ActivityDetailView copyActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public List<String> getActivityAllowedActions(String id, User user) {
        return null;
    }

    @Override
    public String createActivity(SaveActivityParam request, User user) {
        return null;
    }

    @Override
    public void updateActivity(String id, SaveActivityParam request, User user) {

    }

    @Override
    public void deleteActivity(String id, User user) {

    }

    @Override
    public void revokeActivity(String id, User user) {

    }

    @Override
    public void startActivity(String id, StartActivityParam request, User user) {

    }

    @Override
    public void replyActivity(String id, ReplyActivityParam request, User user) {

    }

    @Override
    public void forwardActivity(String id, ForwardActivityParam request, User user) {

    }

    @Override
    public void printActivity(String id, User user) {

    }

    @Override
    public void markActivityAsRead(String id, User user) {

    }

    @Override
    public void markActivityAsDone(String id, User user) {

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
    public Page<ActivityTransitionView> getActivityEndHistory(String id, ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityProcessView> getActivityProcessHistory(String id,
                                                               ActivityActionSearchParam queryRequest) {
        return null;
    }

    @Override
    public List<ActivityProcessView> getActivityProcessHistory(String id, User user) {
        return null;
    }

    @Override
    public Page<ActivityTaskView> getActivityMessages(String id, PageSearchParam queryRequest) {
        return null;
    }

    @Override
    public Receiver buildReceiver(ReceiverParam parameter) {
        return null;
    }

    @Override
    public List<Receiver> buildReceivers(List<ReceiverParam> parameter) {
        return null;
    }
}
