package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;

/**
 */
public class TaskTitleSearchParam extends PageSearchParam {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
