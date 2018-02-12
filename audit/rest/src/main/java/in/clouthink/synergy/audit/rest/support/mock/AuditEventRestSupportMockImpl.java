package in.clouthink.synergy.audit.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.rest.dto.AuditEventQueryParameter;
import in.clouthink.synergy.audit.rest.support.AuditEventRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * AuditEventRestSupport mocker
 *
 * @author dz
 */
@Component
public class AuditEventRestSupportMockImpl implements AuditEventRestSupport {
	@Override
	public Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest) {
		return null;
	}

	@Override
	public AuditEvent getAuditEventDetail(String id) {
		return null;
	}

	@Override
	public void deleteAuditEventsByDay(String realm, Date day, User user) {

	}

	@Override
	public void deleteAuditEventsBeforeDay(String realm, Date day, User user) {

	}
}
