package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.param.ChangeMyPasswordParam;
import in.clouthink.synergy.account.rest.param.ChangeMyProfileParam;
import in.clouthink.synergy.account.rest.view.UserProfileView;
import in.clouthink.synergy.account.rest.support.UserProfileRestSupport;
import in.clouthink.synergy.account.service.UserProfileService;
import in.clouthink.synergy.storage.spi.DownloadUrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserProfileRestSupportImpl implements UserProfileRestSupport {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private DownloadUrlProvider storageService;

	@Override
	public UserProfileView getUserProfile(User user) {
		User result = userProfileService.findUserById(user.getId());
		return UserProfileView.from(result);
	}

	@Override
	public void updateUserProfile(ChangeMyProfileParam request, User byWho) {
		userProfileService.changeUserProfile(byWho.getId(), request);
	}

	@Override
	public void changeMyPassword(ChangeMyPasswordParam request, User byWho) {
		userProfileService.changeUserPassword(byWho.getId(), request.getOldPassword(), request.getNewPassword());
	}

	@Override
	public void updateUserAvatar(String imageId, User byWho) {
		if (StringUtils.isEmpty(imageId)) {
			userProfileService.updateUserAvatar(null, null, byWho);
		}
		else {
			String avatarUrl = storageService.getDownloadUrl(imageId);
			userProfileService.updateUserAvatar(imageId, avatarUrl, byWho);
		}
	}

}
