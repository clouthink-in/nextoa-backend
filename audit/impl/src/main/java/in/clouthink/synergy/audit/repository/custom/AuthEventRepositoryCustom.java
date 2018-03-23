package in.clouthink.synergy.audit.repository.custom;

import in.clouthink.synergy.audit.domain.model.AuthEvent;
import in.clouthink.synergy.audit.domain.request.AuthEventSearchRequest;
import org.springframework.data.domain.Page;

/**
 */
public interface AuthEventRepositoryCustom {

	Page<AuthEvent> queryPage(AuthEventSearchRequest queryRequest);

}
