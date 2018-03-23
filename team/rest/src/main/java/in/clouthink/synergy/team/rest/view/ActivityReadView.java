package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ActivityReadView extends ActivityActionView {

	public static ActivityReadView from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityReadView result = new ActivityReadView();
		convert(action, result);
		result.setLatestReadAt(action.getModifiedAt());
		return result;
	}

	private Date latestReadAt;

	public Date getLatestReadAt() {
		return latestReadAt;
	}

	public void setLatestReadAt(Date latestReadAt) {
		this.latestReadAt = latestReadAt;
	}
}
