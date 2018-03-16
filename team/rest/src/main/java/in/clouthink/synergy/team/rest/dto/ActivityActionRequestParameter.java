package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;

/**
 *
 */
public class ActivityActionRequestParameter extends AbstractActivityRequestParameter implements ForwardActivityRequest, ReplyActivityRequest {

	private String taskId;

	private String content;

	@Override
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
