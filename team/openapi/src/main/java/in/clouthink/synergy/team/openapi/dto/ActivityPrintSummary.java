package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ActivityPrintSummary extends ActivityActionSummary {

	public static ActivityPrintSummary from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityPrintSummary result = new ActivityPrintSummary();
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
