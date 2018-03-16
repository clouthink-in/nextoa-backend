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
		result.setComment(action.getComment());
		return result;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
