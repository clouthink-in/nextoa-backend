package in.clouthink.synergy.team.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedQueryRequest;
import in.clouthink.synergy.team.domain.model.ActivityActionType;

/**
 *
 */
public interface ActivityActionQueryRequest extends DateRangedQueryRequest {

	ActivityActionType[] getActivityActionTypes();

}
