package in.clouthink.nextoa.audit.rest.support;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.audit.domain.model.AuditEvent;
import in.clouthink.nextoa.audit.rest.dto.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuditEventRestSupport {

	Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest);

	AuditEvent getAuditEventDetail(String id);

	void deleteAuditEventsByDay(String realm, Date day, User user);

	void deleteAuditEventsBeforeDay(String realm, Date day, User user);

}
