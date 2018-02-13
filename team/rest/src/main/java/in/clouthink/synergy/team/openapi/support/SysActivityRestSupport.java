package in.clouthink.synergy.team.openapi.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface SysActivityRestSupport {

	Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest);

	ActivityDetail getActivityDetail(String id, User user);

	List<String> getActivityAllowedActions(String id, User user);

	Page<ActivityReadSummary> getActivityReadHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityPrintSummary> getActivityPrintHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityTransitionSummary> getActivityTransitionHistory(String id, ActivityActionQueryParameter queryRequest);

	Page<ActivityProcessSummary> getActivityProcessHistory(String id, ActivityActionQueryParameter queryRequest);

	List<ActivityProcessSummary> getActivityProcessHistory(String id);

	Page<ActivityMessageSummary> getActivityMessages(String id, PageQueryParameter queryRequest);

	void terminateActivity(String id, User user);
}
