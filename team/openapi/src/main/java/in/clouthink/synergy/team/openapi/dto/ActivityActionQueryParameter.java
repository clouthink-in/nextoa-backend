package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.shared.domain.request.DateRangedQueryRequest;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.DateRangedQueryParameter;
import in.clouthink.synergy.team.domain.model.ActivityActionType;
import in.clouthink.synergy.team.domain.request.ActivityActionQueryRequest;

/**
 *
 */
public class ActivityActionQueryParameter extends DateRangedQueryParameter implements ActivityActionQueryRequest {

	private ActivityActionType[] activityActionTypes;

	public ActivityActionQueryParameter() {
	}

	public ActivityActionQueryParameter(PageQueryRequest queryRequest) {
		setStart(queryRequest.getStart());
		setLimit(queryRequest.getLimit());
	}

	public ActivityActionQueryParameter(DateRangedQueryRequest queryRequest) {
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
