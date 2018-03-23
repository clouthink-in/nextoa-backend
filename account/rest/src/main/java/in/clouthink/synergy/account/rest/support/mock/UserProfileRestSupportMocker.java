package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.param.ChangeMyPasswordParam;
import in.clouthink.synergy.account.rest.param.ChangeMyProfileParam;
import in.clouthink.synergy.account.rest.view.UserProfileView;
import in.clouthink.synergy.account.rest.support.UserProfileRestSupport;
import org.springframework.stereotype.Component;

/**
 * UserProfileRestSupport mocker
 *
 * @author dz
 */
@Component
public class UserProfileRestSupportMocker implements UserProfileRestSupport {

	@Override
	public UserProfileView getUserProfile(User byWho) {
		return null;
	}

	@Override
	public void updateUserProfile(ChangeMyProfileParam request, User byWho) {

	}

	@Override
	public void changeMyPassword(ChangeMyPasswordParam request, User byWho) {

	}

	@Override
	public void updateUserAvatar(String imageId, User byWho) {

	}
}
