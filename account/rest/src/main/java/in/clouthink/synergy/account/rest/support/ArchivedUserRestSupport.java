package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.rest.dto.UserDetail;
import in.clouthink.synergy.account.rest.dto.UserQueryParameter;
import in.clouthink.synergy.account.rest.dto.UserSummary;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest);

	UserDetail getArchivedUser(String id);

}
