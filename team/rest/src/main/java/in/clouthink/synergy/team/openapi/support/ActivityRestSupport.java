package in.clouthink.synergy.team.openapi.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ActivityRestSupport {

	Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest, User user);

	Page<ActivitySummary> listDraftActivities(ActivityQueryParameter queryRequest, User user);

	Page<ActivitySummary> listProcessingActivities(ActivityQueryParameter queryRequest, User user);

	Page<ActivitySummary> listRevokedActivities(ActivityQueryParameter queryRequest, User user);

	long countOfAllActivities(ActivityQueryParameter queryRequest, User user);

	long countOfDraftActivities(ActivityQueryParameter queryRequest, User user);

	long countOfProcessingActivities(ActivityQueryParameter queryRequest, User user);

	long countOfRevokedActivities(ActivityQueryParameter queryRequest, User user);

	ActivityDetail getActivityDetail(String id, User user);

	ActivityDetail copyActivityDetail(String id, User user);

	List<String> getActivityAllowedActions(String id, User user);

	String createActivity(SaveActivityParameter request, User user);

	void updateActivity(String id, SaveActivityParameter request, User user);

	void deleteActivity(String id, User user);

	void revokeActivity(String id, User user);

	void startActivity(String id, StartActivityParameter request, User user);

	void replyActivity(String id, ReplyActivityParameter request, User user);

	void forwardActivity(String id, ForwardActivityParameter request, User user);

	void printActivity(String id, User user);

	void markActivityAsRead(String id, User user);

	void markActivityAsDone(String id, User user);

	Page<ActivityReadSummary> getActivityReadHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityPrintSummary> getActivityPrintHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityTransitionSummary> getActivityTransitionHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityTransitionSummary> getActivityEndHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityProcessSummary> getActivityProcessHistory(String id, ActivityActionQueryParameter queryRequest);

	List<ActivityProcessSummary> getActivityProcessHistory(String id, User user);

	void deleteActivityAttachment(String activityId, String fileId, User user);

	Page<ActivityMessageSummary> getActivityMessages(String id, PageQueryParameter queryRequest);

}
