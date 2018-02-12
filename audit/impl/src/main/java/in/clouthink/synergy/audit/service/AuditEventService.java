package in.clouthink.synergy.audit.service;

import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.domain.request.AuditEventQueryRequest;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuditEventService {

	Page<AuditEvent> findAuditEventPage(AuditEventQueryRequest queryRequest);

	AuditEvent findById(String id);

	void deleteAuditEventsByDay(String realm, Date day);

	void deleteAuditEventsBeforeDay(String realm, Date day);

}
