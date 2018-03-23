package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.rest.param.ReceiverView;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class ActivityTransitionView extends ActivityActionView {

	public static ActivityTransitionView from(ActivityAction action) {
		if (action == null) {
			return null;
		}
		ActivityTransitionView result = new ActivityTransitionView();
		convert(action, result);
		result.setTo(ReceiverView.from(action.getToReceivers()));
		result.setCc(ReceiverView.from(action.getCcReceivers()));
		return result;
	}

	private List<ReceiverView> to;

	private List<ReceiverView> cc;

	public List<ReceiverView> getTo() {
		return to;
	}

	public void setTo(List<ReceiverView> to) {
		this.to = to;
	}

	public List<ReceiverView> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverView> cc) {
		this.cc = cc;
	}
}
