package in.clouthink.synergy.audit.repository;


import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.repository.custom.AuditEventRepositoryCustom;
import in.clouthink.synergy.shared.repository.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuditEventRepository extends AbstractRepository<AuditEvent>, AuditEventRepositoryCustom {

	List<AuditEvent> findListByRequestedAtBetween(Date from, Date to);

	long deleteByRealmAndRequestedAtBetween(String realm, Date from, Date to);

	long deleteByRealmAndRequestedAtBefore(String realm, Date day);

}
