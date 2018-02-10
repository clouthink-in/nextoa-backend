package in.clouthink.nextoa.audit.rest.support.mock;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.audit.domain.model.AuthEvent;
import in.clouthink.nextoa.audit.rest.dto.AuthEventQueryParameter;
import in.clouthink.nextoa.audit.rest.support.AuthEventRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * AuthEventRestSupport mocker
 *
 * @author dz
 */
@Component
public class AuthEventRestSupportMockImpl implements AuthEventRestSupport {

	@Override
	public Page<AuthEvent> listAuthEventPage(AuthEventQueryParameter queryRequest) {
		return null;
	}

	@Override
	public AuthEvent getAuthEventDetail(String id) {
		return null;
	}

	@Override
	public void deleteAuthEventsByDay(String realm, Date day, User user) {

	}

	@Override
	public void deleteAuthEventsBeforeDay(String realm, Date day, User user) {

	}
}
