package in.clouthink.synergy.account;

import in.clouthink.synergy.account.domain.model.PasswordSaltProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "synergy.account.password")
public class AccountPasswordConfigurationProperties implements PasswordSaltProvider {

    private String salt = "@account.synergy.clouthink.in";

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
