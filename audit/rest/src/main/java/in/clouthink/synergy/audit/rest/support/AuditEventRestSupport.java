package in.clouthink.synergy.audit.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.rest.param.AuditEventSearchParam;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuditEventRestSupport {

	Page<AuditEvent> listAuditEventPage(AuditEventSearchParam queryRequest);

	AuditEvent getAuditEventDetail(String id);

	void deleteAuditEventsByDay(String realm, Date day, User user);

	void deleteAuditEventsBeforeDay(String realm, Date day, User user);

}
