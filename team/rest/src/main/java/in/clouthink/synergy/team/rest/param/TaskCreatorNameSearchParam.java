package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;

/**
 */
public class TaskCreatorNameSearchParam extends PageSearchParam {

	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
