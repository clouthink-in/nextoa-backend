package in.clouthink.synergy.account.rest.view;

import in.clouthink.synergy.account.domain.model.User;
import io.swagger.annotations.ApiModel;

@ApiModel("用户基本资料")
public class UserProfileView extends UserView {

	public static UserProfileView from(User user) {
		if (user == null) {
			return null;
		}
		UserProfileView result = new UserProfileView();
		convert(user, result);
		return result;
	}

	//TODO add roles

}
