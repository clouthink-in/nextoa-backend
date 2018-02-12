package in.clouthink.synergy.account.rest.dto;

import in.clouthink.synergy.account.domain.model.User;
import io.swagger.annotations.ApiModel;

@ApiModel("用户基本资料")
public class UserProfile extends UserSummary {

	public static UserProfile from(User user) {
		if (user == null) {
			return null;
		}
		UserProfile result = new UserProfile();
		convert(user, result);
		return result;
	}

	//TODO add roles

}
