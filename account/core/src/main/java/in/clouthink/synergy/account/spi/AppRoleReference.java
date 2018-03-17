package in.clouthink.synergy.account.spi;

import in.clouthink.synergy.account.domain.model.Role;

/**
 * The AppRole Refz check, if refz found, the AppRole can not be deleted
 *
 * @author dz
 */
public interface AppRoleReference {

	/**
	 * @param target
	 * @return
	 */
	boolean hasReference(Role target);

}
