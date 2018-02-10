package in.clouthink.nextoa.audit.repository;

import in.clouthink.nextoa.audit.domain.model.AuthEvent;
import in.clouthink.nextoa.audit.repository.custom.AuthEventRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuthEventRepository extends AbstractRepository<AuthEvent>, AuthEventRepositoryCustom {

	List<AuthEvent> findListByLoginAtBetween(Date from, Date to);

	long deleteByRealmAndLoginAtBetween(String realm, Date from, Date to);

	long deleteByRealmAndLoginAtBefore(String realm, Date day);

}
