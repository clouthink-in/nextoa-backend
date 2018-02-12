package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.ChangeMyPasswordRequest;
import in.clouthink.synergy.account.rest.dto.ChangeMyProfileParameter;
import in.clouthink.synergy.account.rest.dto.UserProfile;
import in.clouthink.synergy.account.rest.support.UserProfileRestSupport;
import org.springframework.stereotype.Component;

/**
 * UserProfileRestSupport mocker
 *
 * @author dz
 */
@Component
public class UserProfileRestSupportMockImpl implements UserProfileRestSupport {
	@Override
	public UserProfile getUserProfile(User byWho) {
		return null;
	}

	@Override
	public void updateUserProfile(ChangeMyProfileParameter request, User byWho) {

	}

	@Override
	public void changeMyPassword(ChangeMyPasswordRequest request, User byWho) {

	}

	@Override
	public void updateUserAvatar(String imageId, User byWho) {

	}
}
