package in.clouthink.nextoa.audit.repository;


import in.clouthink.nextoa.audit.domain.model.AuditEvent;
import in.clouthink.nextoa.audit.repository.custom.AuditEventRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuditEventRepository extends AbstractRepository<AuditEvent>, AuditEventRepositoryCustom {

	List<AuditEvent> findListByRequestedAtBetween(Date from, Date to);

	long deleteByRealmAndRequestedAtBetween(String realm, Date from, Date to);

	long deleteByRealmAndRequestedAtBefore(String realm, Date day);

}
