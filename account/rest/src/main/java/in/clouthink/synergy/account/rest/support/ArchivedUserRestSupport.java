package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.rest.view.UserDetailView;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.view.UserView;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserView> listArchivedUsers(UserSearchParam queryRequest);

	UserDetailView getArchivedUser(String id);

}
