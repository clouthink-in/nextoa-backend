package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ActivityProcessSummary extends ActivityActionSummary {

	public static ActivityProcessSummary from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityProcessSummary result = new ActivityProcessSummary();
		convert(action, result);
		result.setContent(action.getContent());
		return result;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
