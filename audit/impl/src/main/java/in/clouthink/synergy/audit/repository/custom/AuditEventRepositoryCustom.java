package in.clouthink.synergy.audit.repository.custom;

import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.domain.request.AuditEventQueryRequest;
import org.springframework.data.domain.Page;

public interface AuditEventRepositoryCustom {

	Page<AuditEvent> queryPage(AuditEventQueryRequest queryRequest);

}
