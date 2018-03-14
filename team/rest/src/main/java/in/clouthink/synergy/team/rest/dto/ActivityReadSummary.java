package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ActivityReadSummary extends ActivityActionSummary {

	public static ActivityReadSummary from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityReadSummary result = new ActivityReadSummary();
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
