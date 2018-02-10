package in.clouthink.nextoa.audit.repository.custom;

import in.clouthink.nextoa.audit.domain.model.AuditEvent;
import in.clouthink.nextoa.audit.domain.request.AuditEventQueryRequest;
import org.springframework.data.domain.Page;

public interface AuditEventRepositoryCustom {

	Page<AuditEvent> queryPage(AuditEventQueryRequest queryRequest);

}
