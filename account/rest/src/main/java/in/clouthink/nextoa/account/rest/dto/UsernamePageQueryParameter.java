package in.clouthink.nextoa.account.rest.dto;

import in.clouthink.nextoa.account.domain.request.UsernameQueryRequest;
import in.clouthink.nextoa.shared.domain.request.impl.DateRangedQueryParameter;
import io.swagger.annotations.ApiModel;

@ApiModel("按用户名查询申请")
public class UsernamePageQueryParameter extends DateRangedQueryParameter implements UsernameQueryRequest {

	private String username;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
