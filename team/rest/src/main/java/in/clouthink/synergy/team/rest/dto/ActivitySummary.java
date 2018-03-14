package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.model.ActivityStatus;
import in.clouthink.synergy.team.domain.model.ActivityType;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ActivitySummary {

	static void convert(Activity activity, ActivitySummary result) {
		result.setId(activity.getId());
		result.setType(activity.getType());
		result.setTitle(activity.getTitle());
		result.setCreatorId(activity.getCreatedBy().getId());
		result.setCreatorName(activity.getCreatedBy().getUsername());
//		if (activity.getCreatedBy().getOrganizations() != null) {
//			result.setCreatorOrganizations(activity.getCreatedBy()
//												.getOrganizations()
//												.stream()
//												.map(SimpleOrganization::from)
//												.collect(Collectors.toList()));
//		}

		result.setCreatedAt(activity.getCreatedAt());
		result.setStatus(activity.getStatus());
		result.setCategory(activity.getCategory());
		result.setUrgent(activity.isUrgent());
		result.setBusinessComplete(activity.isBusinessComplete());
		ActivityAction activityAction = activity.getLatestActivityAction();

		if (activityAction != null) {
			result.setHandlerId(activityAction.getCreatedBy().getId());
			result.setHandlerName(activityAction.getCreatedBy().getUsername());
			result.setHandledAt(activityAction.getCreatedAt());
		}
		else {
			result.setHandlerName(activity.getCreatedBy().getUsername());
			result.setHandledAt(activity.getCreatedAt());
		}
	}

	public static ActivitySummary from(Activity activity) {
		if (activity == null) {
			return null;
		}
		ActivitySummary result = new ActivitySummary();
		convert(activity, result);
		return result;
	}

	private String id;

	private ActivityType type;

	private String category;

	private String title;

	private String creatorId;

	private String creatorName;

//	private List<SimpleOrganization> creatorOrganizations;

	private Date createdAt;

	private String handlerId;

	private String handlerName;

	private Date handledAt;

	private ActivityStatus status;

	// 是否已收藏
	private boolean favorite = false;

	// 是否已读
	private boolean read = false;

	// 是否紧急
	private boolean urgent = false;

	// （如果带业务,业务是否完成）
	private boolean businessComplete = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Date getHandledAt() {
		return handledAt;
	}

	public void setHandledAt(Date handledAt) {
		this.handledAt = handledAt;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isBusinessComplete() {
		return businessComplete;
	}

	public void setBusinessComplete(boolean businessComplete) {
		this.businessComplete = businessComplete;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

//	public List<SimpleOrganization> getCreatorOrganizations() {
//		return creatorOrganizations;
//	}
//
//	public void setCreatorOrganizations(List<SimpleOrganization> creatorOrganizations) {
//		this.creatorOrganizations = creatorOrganizations;
//	}
}
