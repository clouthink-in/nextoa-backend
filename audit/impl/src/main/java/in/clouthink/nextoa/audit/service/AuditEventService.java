package in.clouthink.nextoa.audit.service;

import in.clouthink.nextoa.audit.domain.model.AuditEvent;
import in.clouthink.nextoa.audit.domain.request.AuditEventQueryRequest;
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
