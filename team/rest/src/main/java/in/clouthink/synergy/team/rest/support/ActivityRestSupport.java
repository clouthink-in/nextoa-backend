package in.clouthink.synergy.team.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.*;
import in.clouthink.synergy.team.rest.view.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ActivityRestSupport {

	Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest, User user);

	Page<ActivityView> listDraftActivities(ActivitySearchParam queryRequest, User user);

	Page<ActivityView> listProcessingActivities(ActivitySearchParam queryRequest, User user);

	Page<ActivityView> listRevokedActivities(ActivitySearchParam queryRequest, User user);

	long countOfAllActivities(ActivitySearchParam queryRequest, User user);

	long countOfDraftActivities(ActivitySearchParam queryRequest, User user);

	long countOfProcessingActivities(ActivitySearchParam queryRequest, User user);

	long countOfRevokedActivities(ActivitySearchParam queryRequest, User user);

	ActivityDetailView getActivityDetail(String id, User user);

	ActivityDetailView copyActivityDetail(String id, User user);

	List<String> getActivityAllowedActions(String id, User user);

	String createActivity(SaveActivityParam request, User user);

	void updateActivity(String id, SaveActivityParam request, User user);

	void deleteActivity(String id, User user);

	void revokeActivity(String id, User user);

	void startActivity(String id, StartActivityParam request, User user);

	void replyActivity(String id, ReplyActivityParam request, User user);

	void forwardActivity(String id, ForwardActivityParam request, User user);

	void printActivity(String id, User user);

	void markActivityAsRead(String id, User user);

	void markActivityAsDone(String id, User user);

	Page<ActivityReadView> getActivityReadHistory(String id, ActivityActionSearchParam queryRequest);

	Page<ActivityPrintView> getActivityPrintHistory(String id, ActivityActionSearchParam queryRequest);

	Page<ActivityTransitionView> getActivityTransitionHistory(String id, ActivityActionSearchParam queryRequest);

	Page<ActivityTransitionView> getActivityEndHistory(String id, ActivityActionSearchParam queryRequest);

	Page<ActivityProcessView> getActivityProcessHistory(String id, ActivityActionSearchParam queryRequest);

	List<ActivityProcessView> getActivityProcessHistory(String id, User user);

	Page<ActivityTaskView> getActivityTasks(String id, PageSearchParam queryRequest);

}
