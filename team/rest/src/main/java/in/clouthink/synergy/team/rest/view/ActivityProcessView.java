package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ActivityProcessView extends ActivityActionView {

	public static ActivityProcessView from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityProcessView result = new ActivityProcessView();
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
