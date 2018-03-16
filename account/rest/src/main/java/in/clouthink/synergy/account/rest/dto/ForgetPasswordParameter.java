package in.clouthink.synergy.account.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("忘记用户密码")
public class ForgetPasswordParameter {

	private String telephone;

	private String passcode;

	private String newPassword;

	private String renewPassword;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRenewPassword() {
		return renewPassword;
	}

	public void setRenewPassword(String renewPassword) {
		this.renewPassword = renewPassword;
	}
}
