package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.request.ReadActivityEvent;

/**
 */
public class ReadActivityEventObject implements ReadActivityEvent {

	private Activity activity;

	private User user;

	public ReadActivityEventObject(Activity activity, User user) {
		this.activity = activity;
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	@Override
	public User getUser() {
		return user;
	}
}
