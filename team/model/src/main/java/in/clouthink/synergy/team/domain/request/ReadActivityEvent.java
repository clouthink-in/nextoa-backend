package in.clouthink.synergy.team.domain.request;


import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;

/**
 */
public interface ReadActivityEvent {

	String EVENT_NAME = ReadActivityEvent.class.getName();

	Activity getActivity();

	User getUser();

}
