package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.shared.domain.request.impl.DateRangedQueryParameter;
import in.clouthink.synergy.team.domain.model.TaskStatus;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class TaskQueryParameter extends DateRangedQueryParameter implements TaskQueryRequest {

	private String category;

	private String title;

	private String initiatorUsername;

	private TaskStatus taskStatus;

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

	public String getInitiatorUsername() {
		return initiatorUsername;
	}

	public void setInitiatorUsername(String initiatorUsername) {
		this.initiatorUsername = initiatorUsername;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
}
