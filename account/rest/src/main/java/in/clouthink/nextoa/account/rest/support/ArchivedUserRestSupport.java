package in.clouthink.nextoa.account.rest.support;

import in.clouthink.nextoa.account.rest.dto.UserDetail;
import in.clouthink.nextoa.account.rest.dto.UserQueryParameter;
import in.clouthink.nextoa.account.rest.dto.UserSummary;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest);

	UserDetail getArchivedUser(String id);

}
