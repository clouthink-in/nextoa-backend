package in.clouthink.synergy.team.rest.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ForwardActivityParameter extends AbstractActivityParameter {

	private String taskId;

	private String comment;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
