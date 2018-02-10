package in.clouthink.nextoa.menu.rest.support.mock;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.menu.rest.dto.Menu;
import in.clouthink.nextoa.menu.rest.support.UserProfileExtensionRestSupport;
import in.clouthink.nextoa.rbac.model.Action;
import in.clouthink.nextoa.rbac.model.Action;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserProfileExtensionRestSupport mocker
 *
 * @author dz
 */
@Component
public class UserProfileExtensionRestSupportMockImpl implements UserProfileExtensionRestSupport {

	@Override
	public List<Menu> getGrantedMenus(User user) {
		return null;
	}

	@Override
	public List<Action> getGrantedActions(String code, User user) {
		return null;
	}
}
