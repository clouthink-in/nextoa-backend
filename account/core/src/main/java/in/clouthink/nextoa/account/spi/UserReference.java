package in.clouthink.nextoa.account.spi;

import in.clouthink.nextoa.account.domain.model.User;

/**
 * The User Refz check, if refz found, the User can not be deleted
 *
 * @author dz
 */
public interface UserReference {

	/**
	 * @param target
	 * @return
	 */
	boolean hasReference(User target);

}
