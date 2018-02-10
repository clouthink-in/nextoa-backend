package in.clouthink.nextoa.audit.repository.custom;

import in.clouthink.nextoa.audit.domain.model.AuthEvent;
import in.clouthink.nextoa.audit.domain.request.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 */
public interface AuthEventRepositoryCustom {

	Page<AuthEvent> queryPage(AuthEventQueryRequest queryRequest);

}
