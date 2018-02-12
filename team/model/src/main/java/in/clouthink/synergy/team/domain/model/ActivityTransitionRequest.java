package in.clouthink.synergy.team.domain.model;

/**
 * The activity transition request (from -> to)
 */
public class ActivityTransitionRequest {

	private ActivityAction fromAction;

	private ActivityAction toAction;

	public ActivityTransitionRequest(ActivityAction fromAction, ActivityAction toAction) {
		this.fromAction = fromAction;
		this.toAction = toAction;
	}

	public ActivityAction getFromAction() {
		return fromAction;
	}

	public ActivityAction getToAction() {
		return toAction;
	}

}
