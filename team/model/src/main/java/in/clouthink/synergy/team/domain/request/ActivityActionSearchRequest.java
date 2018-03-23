package in.clouthink.synergy.team.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import in.clouthink.synergy.team.domain.model.ActivityActionType;

/**
 *
 */
public interface ActivityActionSearchRequest extends DateRangedSearchRequest {

	ActivityActionType[] getActivityActionTypes();

}
