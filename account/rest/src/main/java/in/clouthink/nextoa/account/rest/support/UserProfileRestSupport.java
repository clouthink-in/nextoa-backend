package in.clouthink.nextoa.account.rest.support;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.account.rest.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.account.rest.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.account.rest.dto.UserProfile;

/**
 *
 */
public interface UserProfileRestSupport {

	/**
	 *
	 * @param byWho
	 * @return
	 */
	UserProfile getUserProfile(User byWho);

	/**
	 *
	 * @param request
	 * @param byWho
	 */
	void updateUserProfile(ChangeMyProfileParameter request, User byWho);

	/**
	 *
	 * @param request
	 * @param byWho
	 */
	void changeMyPassword(ChangeMyPasswordRequest request, User byWho);

	/**
	 * 更新用户头像
	 *
	 * @param imageId 如果为空,则清空用户头像
	 * @param byWho
	 */
	void updateUserAvatar(String imageId, User byWho);

}
