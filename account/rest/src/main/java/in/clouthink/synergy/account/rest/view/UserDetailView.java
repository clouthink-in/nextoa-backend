package in.clouthink.synergy.account.rest.view;

import in.clouthink.synergy.account.domain.model.User;
import io.swagger.annotations.ApiModel;

@ApiModel("用户明细")
public class UserDetailView extends UserView {

	static void convert(User user, UserDetailView result) {
		UserView.convert(user, result);
	}

	public static UserDetailView from(User user) {
		if (user == null) {
			return null;
		}
		UserDetailView result = new UserDetailView();
		convert(user, result);
		return result;
	}

	private boolean followed;

	private String email;

	private String signature;

	private boolean socialGroupManager;

	public boolean isFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean isSocialGroupManager() {
		return socialGroupManager;
	}

	public void setSocialGroupManager(boolean socialGroupManager) {
		this.socialGroupManager = socialGroupManager;
	}

}
