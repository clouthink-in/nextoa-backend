package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;

/**
 *
 */
public class ActivityActionRequestParameter extends AbstractActivityRequestParameter implements ForwardActivityRequest, ReplyActivityRequest {

	private String messageId;

	private String content;

	@Override
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
