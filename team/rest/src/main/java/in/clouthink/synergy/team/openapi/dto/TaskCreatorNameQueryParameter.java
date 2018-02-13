package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;

/**
 */
public class TaskCreatorNameQueryParameter extends PageQueryParameter {

	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
