package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.BizRefType;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.TaskStatus;
import in.clouthink.synergy.team.domain.model.Activity;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 *
 */
@ApiModel
public class TaskSummary {

	static void convert(Task task, Activity activity, TaskSummary result) {
		BeanUtils.copyProperties(task, result, "sender", "receiver", "attributes");
		if (task.getSender() != null) {
			result.setSentById(task.getSender().getId());
			result.setSentByName(task.getSender().getUsername());
		}
		result.setUrgent(activity.isUrgent());
		result.setActivityAuthorName(activity.getCreatedBy().getUsername());
		if (activity.getLatestActivityAction() != null) {
			result.setLatestHandlerName(activity.getLatestActivityAction().getCreatedBy().getUsername());
			result.setLatestHandledAt(activity.getLatestActivityAction().getCreatedAt());
		}
	}

	public static TaskSummary from(Task task, Activity activity) {
		if (task == null) {
			return null;
		}
		TaskSummary result = new TaskSummary();
		convert(task, activity, result);
		return result;
	}

	private String id;

	// 业务主体（协作请求）的类型
	@Indexed
	private BizRefType bizRefType;

	// 业务主体（协作请求）的引用
	@Indexed
	private String bizRefId;

	private String category;

	private String title;

	// 任务接受时间（也是任务创建的时间）
	private Date receivedAt;

	// 创建人（指协作请求最开始的创建人）
	private String activityAuthorName;

	// 发送人（上一个处理人）
	private String sentById;

	// 发送人（上一个处理人）
	private String sentByName;

	// 最近处理人（回复,转发均可）
	// from biz
	private String latestHandlerName;

	// 最近处理时间（回复,转发均可）
	// from biz
	private Date latestHandledAt;

	// 任务状态
	private TaskStatus status;

	//是否已收藏
	private boolean favorite = false;

	//是否已读
	private boolean read = false;

	//是否紧急
	private boolean urgent = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BizRefType getBizRefType() {
		return bizRefType;
	}

	public void setBizRefType(BizRefType bizRefType) {
		this.bizRefType = bizRefType;
	}

	public String getBizRefId() {
		return bizRefId;
	}

	public void setBizRefId(String bizRefId) {
		this.bizRefId = bizRefId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivityAuthorName() {
		return activityAuthorName;
	}

	public void setActivityAuthorName(String activityAuthorName) {
		this.activityAuthorName = activityAuthorName;
	}

	public String getSentById() {
		return sentById;
	}

	public void setSentById(String sentById) {
		this.sentById = sentById;
	}

	public String getSentByName() {
		return sentByName;
	}

	public void setSentByName(String sentByName) {
		this.sentByName = sentByName;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getLatestHandlerName() {
		return latestHandlerName;
	}

	public void setLatestHandlerName(String latestHandlerName) {
		this.latestHandlerName = latestHandlerName;
	}

	public Date getLatestHandledAt() {
		return latestHandledAt;
	}

	public void setLatestHandledAt(Date latestHandledAt) {
		this.latestHandledAt = latestHandledAt;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
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
}
