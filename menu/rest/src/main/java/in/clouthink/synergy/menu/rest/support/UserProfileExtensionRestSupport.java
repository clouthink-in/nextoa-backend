package in.clouthink.synergy.menu.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.menu.rest.dto.Menu;
import in.clouthink.synergy.rbac.model.Action;

import java.util.List;

/**
 *
 */
public interface UserProfileExtensionRestSupport {

	/**
	 *
	 * @param user
	 * @return
	 */
	List<Menu> getGrantedMenus(User user);

	/**
	 *
	 * @param code
	 * @param user
	 * @return
	 */
	List<Action> getGrantedActions(String code, User user);
}
