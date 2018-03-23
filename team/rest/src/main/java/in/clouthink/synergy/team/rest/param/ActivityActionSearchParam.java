package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.DateRangedSearchParam;
import in.clouthink.synergy.team.domain.model.ActivityActionType;
import in.clouthink.synergy.team.domain.request.ActivityActionSearchRequest;

/**
 *
 */
public class ActivityActionSearchParam extends DateRangedSearchParam implements ActivityActionSearchRequest {

	private ActivityActionType[] activityActionTypes;

	public ActivityActionSearchParam() {
	}

	public ActivityActionSearchParam(PageSearchRequest queryRequest) {
		setStart(queryRequest.getStart());
		setLimit(queryRequest.getLimit());
	}

	public ActivityActionSearchParam(DateRangedSearchRequest queryRequest) {
		setStart(queryRequest.getStart());
		setLimit(queryRequest.getLimit());
		setBeginDate(queryRequest.getBeginDate());
		setEndDate(queryRequest.getEndDate());
	}

	public ActivityActionType[] getActivityActionTypes() {
		return activityActionTypes;
	}

	public void setActivityActionTypes(ActivityActionType... activityActionTypes) {
		this.activityActionTypes = activityActionTypes;
	}
}
