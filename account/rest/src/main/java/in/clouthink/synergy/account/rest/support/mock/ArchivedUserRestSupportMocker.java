package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.rest.view.UserDetailView;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.view.UserView;
import in.clouthink.synergy.account.rest.support.ArchivedUserRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * ArchivedUserRestSupport mocker
 *
 * @author dz
 */
@Component
public class ArchivedUserRestSupportMocker implements ArchivedUserRestSupport {

	@Override
	public Page<UserView> listArchivedUsers(UserSearchParam queryRequest) {
		return null;
	}

	@Override
	public UserDetailView getArchivedUser(String id) {
		return null;
	}
}
