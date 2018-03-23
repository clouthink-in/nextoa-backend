package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.shared.domain.request.impl.DateRangedSearchParam;
import in.clouthink.synergy.team.domain.model.ActivityStatus;
import in.clouthink.synergy.team.domain.request.ActivitySearchRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class ActivitySearchParam extends DateRangedSearchParam implements ActivitySearchRequest {

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
