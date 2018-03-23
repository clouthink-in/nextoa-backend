package in.clouthink.synergy.team.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import in.clouthink.synergy.team.domain.model.ActivityStatus;

/**
 *
 */
public interface ActivitySearchRequest extends DateRangedSearchRequest {

	enum IncludeOrExcludeStatus {
		INCLUDE , EXCLUDE
	}

	String getCategory();

	String getTitle();

	ActivityStatus getActivityStatus();

	Boolean getUrgent();

	String getCreatorId();

	String getCreatorUsername();

}
