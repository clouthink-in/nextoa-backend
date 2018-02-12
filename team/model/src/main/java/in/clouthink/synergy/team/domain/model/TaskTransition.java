package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 任务状态变迁历史纪录
 */
@Document(collection = "TaskTransitions")
public class TaskTransition extends StringIdentifier {

	private String bizActionRefId;

	@Indexed
	@DBRef
	private Task task;

	private TaskStatus fromStatus;

	private TaskStatus toStatus;

	@DBRef(lazy = true)
	private User transitedBy;

	private Date transitedAt;

	public String getBizActionRefId() {
		return bizActionRefId;
	}

	public void setBizActionRefId(String bizActionRefId) {
		this.bizActionRefId = bizActionRefId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public TaskStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(TaskStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	public TaskStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(TaskStatus toStatus) {
		this.toStatus = toStatus;
	}

	public User getTransitedBy() {
		return transitedBy;
	}

	public void setTransitedBy(User transitedBy) {
		this.transitedBy = transitedBy;
	}

	public Date getTransitedAt() {
		return transitedAt;
	}

	public void setTransitedAt(Date transitedAt) {
		this.transitedAt = transitedAt;
	}
}
