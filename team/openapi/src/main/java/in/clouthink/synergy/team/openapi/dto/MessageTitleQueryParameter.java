package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;

/**
 */
public class MessageTitleQueryParameter extends PageQueryParameter {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
