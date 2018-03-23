package in.clouthink.synergy.menu.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.menu.rest.view.MenuView;
import in.clouthink.synergy.menu.rest.support.UserProfileExtensionRestSupport;
import in.clouthink.synergy.rbac.model.Action;
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
	public List<MenuView> getGrantedMenus(User user) {
		return null;
	}

	@Override
	public List<Action> getGrantedActions(String code, User user) {
		return null;
	}
}
