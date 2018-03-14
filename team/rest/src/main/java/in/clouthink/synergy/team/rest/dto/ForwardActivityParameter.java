package in.clouthink.synergy.team.rest.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ForwardActivityParameter extends AbstractActivityParameter {

	private String messageId;

	private String content;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
