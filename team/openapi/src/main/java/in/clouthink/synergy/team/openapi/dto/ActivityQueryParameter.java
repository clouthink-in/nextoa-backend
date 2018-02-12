package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.shared.domain.request.impl.DateRangedQueryParameter;
import in.clouthink.synergy.team.domain.model.ActivityStatus;
import in.clouthink.synergy.team.domain.request.ActivityQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class ActivityQueryParameter extends DateRangedQueryParameter implements ActivityQueryRequest {

	private String category;

	private String title;

	private ActivityStatus activityStatus;

	private Boolean urgent;

	private String creatorId;

	private String creatorUsername;

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ActivityStatus getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(ActivityStatus activityStatus) {
		this.activityStatus = activityStatus;
	}

	@Override
	public Boolean getUrgent() {
		return urgent;
	}

	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}

	@Override
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}
}
