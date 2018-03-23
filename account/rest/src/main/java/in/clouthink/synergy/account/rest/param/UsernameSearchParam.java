package in.clouthink.synergy.account.rest.param;

import in.clouthink.synergy.account.domain.request.UsernameSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.DateRangedSearchParam;
import io.swagger.annotations.ApiModel;

@ApiModel("按用户名查询申请")
public class UsernameSearchParam extends DateRangedSearchParam implements UsernameSearchRequest {

	private String username;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
