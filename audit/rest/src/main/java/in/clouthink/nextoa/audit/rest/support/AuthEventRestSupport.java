package in.clouthink.nextoa.audit.rest.support;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.audit.domain.model.AuthEvent;
import in.clouthink.nextoa.audit.rest.dto.AuthEventQueryParameter;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuthEventRestSupport {

	Page<AuthEvent> listAuthEventPage(AuthEventQueryParameter queryRequest);

	AuthEvent getAuthEventDetail(String id);

	void deleteAuthEventsByDay(String realm, Date day, User user);

	void deleteAuthEventsBeforeDay(String realm, Date day, User user);
}
