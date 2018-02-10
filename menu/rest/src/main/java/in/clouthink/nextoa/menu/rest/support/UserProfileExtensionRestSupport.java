package in.clouthink.nextoa.menu.rest.support;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.menu.rest.dto.Menu;
import in.clouthink.nextoa.rbac.model.Action;
import in.clouthink.nextoa.rbac.model.Action;

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
