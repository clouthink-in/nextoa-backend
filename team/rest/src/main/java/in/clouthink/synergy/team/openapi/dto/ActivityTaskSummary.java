package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.TaskStatus;
import io.swagger.annotations.ApiModel;

import java.util.Date;


/**
 */
@ApiModel
public class ActivityTaskSummary {

	static void convert(Task task, ActivityTaskSummary result) {
		result.setId(task.getId());
		result.setStatus(task.getStatus());
		result.setReceivedAt(task.getReceivedAt());
		result.setProcessedAt(task.getModifiedAt());
		if (task.getSender() != null) {
			result.setSenderId(task.getSender().getId());
			result.setSenderName(task.getSender().getUsername());
		}
		if (task.getReceiver() != null) {
			result.setReceiverId(task.getReceiver().getId());
			result.setReceiverName(task.getReceiver().getUsername());
		}
	}

	public static ActivityTaskSummary from(Task task) {
		if (task == null) {
			return null;
		}
		ActivityTaskSummary result = new ActivityTaskSummary();
		convert(task, result);
		return result;
	}

	private String id;

	private String receiverId;

	private String receiverName;

	private String senderId;

	private String senderName;

	private Date receivedAt;

	private Date processedAt;

	private TaskStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Date getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(Date processedAt) {
		this.processedAt = processedAt;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
