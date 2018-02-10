package in.clouthink.nextoa.audit.service;


import in.clouthink.nextoa.audit.domain.model.AuthEvent;
import in.clouthink.nextoa.audit.domain.request.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * the auth(login) event service
 */
public interface AuthEventService {

	Page<AuthEvent> listUserAuthEvents(AuthEventQueryRequest queryParameter);

	AuthEvent findUserAuthEventById(String id);

	AuthEvent saveUserAuthEvent(AuthEvent authEvent);

	void deleteAuthEventsByDay(String realm, Date day);

	void deleteAuthEventsBeforeDay(String realm, Date day);

}
