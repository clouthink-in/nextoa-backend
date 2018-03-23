package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.param.ChangeMyPasswordParam;
import in.clouthink.synergy.account.rest.param.ChangeMyProfileParam;
import in.clouthink.synergy.account.rest.view.UserProfileView;

/**
 *
 */
public interface UserProfileRestSupport {

	/**
	 *
	 * @param byWho
	 * @return
	 */
	UserProfileView getUserProfile(User byWho);

	/**
	 *
	 * @param request
	 * @param byWho
	 */
	void updateUserProfile(ChangeMyProfileParam request, User byWho);

	/**
	 *
	 * @param request
	 * @param byWho
	 */
	void changeMyPassword(ChangeMyPasswordParam request, User byWho);

	/**
	 * 更新用户头像
	 *
	 * @param imageId 如果为空,则清空用户头像
	 * @param byWho
	 */
	void updateUserAvatar(String imageId, User byWho);

}
