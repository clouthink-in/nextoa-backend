package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class ActivityTransitionSummary extends ActivityActionSummary {

	public static ActivityTransitionSummary from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityTransitionSummary result = new ActivityTransitionSummary();
		convert(action, result);
		result.setTo(ReceiverSummary.from(action.getToReceivers()));
		result.setCc(ReceiverSummary.from(action.getCcReceivers()));
		return result;
	}

	private List<ReceiverSummary> to;

	private List<ReceiverSummary> cc;

	public List<ReceiverSummary> getTo() {
		return to;
	}

	public void setTo(List<ReceiverSummary> to) {
		this.to = to;
	}

	public List<ReceiverSummary> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverSummary> cc) {
		this.cc = cc;
	}
}
