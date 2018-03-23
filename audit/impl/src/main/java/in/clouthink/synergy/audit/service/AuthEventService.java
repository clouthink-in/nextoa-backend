package in.clouthink.synergy.audit.service;


import in.clouthink.synergy.audit.domain.model.AuthEvent;
import in.clouthink.synergy.audit.domain.request.AuthEventSearchRequest;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * the auth(login) event service
 */
public interface AuthEventService {

	Page<AuthEvent> listUserAuthEvents(AuthEventSearchRequest queryParameter);

	AuthEvent findUserAuthEventById(String id);

	AuthEvent saveUserAuthEvent(AuthEvent authEvent);

	void deleteAuthEventsByDay(String realm, Date day);

	void deleteAuthEventsBeforeDay(String realm, Date day);

}
