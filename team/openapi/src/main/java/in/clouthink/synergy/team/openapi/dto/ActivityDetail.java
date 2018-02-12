package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityActionType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class ActivityDetail extends ActivitySummary {

	public static ActivityDetail from(Activity activity) {
		if (activity == null) {
			return null;
		}
		ActivityDetail result = new ActivityDetail();
		convert(activity, result);
		result.setContent(activity.getContent());
		result.setAllowedActions(activity.getAllowedActions());
		return result;
	}

	private String content;

	private List<ActivityActionType> allowedActions;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ActivityActionType> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<ActivityActionType> allowedActions) {
		this.allowedActions = allowedActions;
	}

}
