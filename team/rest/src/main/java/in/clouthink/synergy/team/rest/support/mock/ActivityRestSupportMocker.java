package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.ActivityQueryRequest;
import in.clouthink.synergy.team.exception.ActivityNotFoundException;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import in.clouthink.synergy.team.rest.support.ReceiverBuilder;
import in.clouthink.synergy.team.service.ActivityService;
import in.clouthink.synergy.team.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityRestSupportMocker implements ActivityRestSupport, ReceiverBuilder {

    @Override
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivitySummary> listDraftActivities(ActivityQueryParameter queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivitySummary> listProcessingActivities(ActivityQueryParameter queryRequest, User user) {
        return null;
    }

    @Override
    public Page<ActivitySummary> listRevokedActivities(ActivityQueryParameter queryRequest, User user) {
        return null;
    }

    @Override
    public long countOfAllActivities(ActivityQueryParameter queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfDraftActivities(ActivityQueryParameter queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfProcessingActivities(ActivityQueryParameter queryRequest, User user) {
        return 0;
    }

    @Override
    public long countOfRevokedActivities(ActivityQueryParameter queryRequest, User user) {
        return 0;
    }

    @Override
    public ActivityDetail getActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public ActivityDetail copyActivityDetail(String id, User user) {
        return null;
    }

    @Override
    public List<String> getActivityAllowedActions(String id, User user) {
        return null;
    }

    @Override
    public String createActivity(SaveActivityParameter request, User user) {
        return null;
    }

    @Override
    public void updateActivity(String id, SaveActivityParameter request, User user) {

    }

    @Override
    public void deleteActivity(String id, User user) {

    }

    @Override
    public void revokeActivity(String id, User user) {

    }

    @Override
    public void startActivity(String id, StartActivityParameter request, User user) {

    }

    @Override
    public void replyActivity(String id, ReplyActivityParameter request, User user) {

    }

    @Override
    public void forwardActivity(String id, ForwardActivityParameter request, User user) {

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
    public Page<ActivityTransitionSummary> getActivityEndHistory(String id, ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public Page<ActivityProcessSummary> getActivityProcessHistory(String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        return null;
    }

    @Override
    public List<ActivityProcessSummary> getActivityProcessHistory(String id, User user) {
        return null;
    }

    @Override
    public Page<ActivityTaskSummary> getActivityMessages(String id, PageQueryParameter queryRequest) {
        return null;
    }

    @Override
    public Receiver buildReceiver(ReceiverParameter parameter) {
        return null;
    }

    @Override
    public List<Receiver> buildReceivers(List<ReceiverParameter> parameter) {
        return null;
    }
}
