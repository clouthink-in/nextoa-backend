package in.clouthink.synergy.shared.domain.request.impl;

import in.clouthink.synergy.shared.domain.request.PublishableSearchRequest;
import io.swagger.annotations.ApiModel;

@ApiModel
public class PublishableSearchParam extends PageSearchParam implements PublishableSearchRequest {

	private Boolean published;

	public PublishableSearchParam() {
	}

	public PublishableSearchParam(int start, int limit) {
		super(start, limit);
	}

	@Override
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

}
