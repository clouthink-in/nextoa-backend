package in.clouthink.nextoa.account.rest.support.mock;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.account.rest.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.account.rest.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.account.rest.dto.UserProfile;
import in.clouthink.nextoa.account.rest.support.UserProfileRestSupport;
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
