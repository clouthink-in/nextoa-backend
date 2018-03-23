package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ActivityPrintView extends ActivityActionView {

	public static ActivityPrintView from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityPrintView result = new ActivityPrintView();
		convert(action, result);
		result.setLatestPrintAt(action.getModifiedAt());
		return result;
	}

	private Date latestPrintAt;

	public Date getLatestPrintAt() {
		return latestPrintAt;
	}

	public void setLatestPrintAt(Date latestPrintAt) {
		this.latestPrintAt = latestPrintAt;
	}

}
