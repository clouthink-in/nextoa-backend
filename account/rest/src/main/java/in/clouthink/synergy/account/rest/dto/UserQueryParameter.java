package in.clouthink.synergy.account.rest.dto;

import in.clouthink.synergy.account.domain.model.Gender;
import in.clouthink.synergy.account.domain.request.UserQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.DateRangedQueryParameter;
import io.swagger.annotations.ApiModel;

@ApiModel("查询用户申请")
public class UserQueryParameter extends DateRangedQueryParameter implements UserQueryRequest {

	private String username;

	// 联系电话
	private String telephone;

	//电子邮箱
	private String email;

	private String displayName;

	private Gender gender;

	private Boolean enabled;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
