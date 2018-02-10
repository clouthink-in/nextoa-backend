package in.clouthink.nextoa.account;

import in.clouthink.nextoa.account.domain.model.PasswordSaltProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "in.clouthink.nextoa.account.password")
public class AccountPasswordConfigurationProperties implements PasswordSaltProvider {

	private String salt = "@account.sbb.daas.clouthink.in";

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
