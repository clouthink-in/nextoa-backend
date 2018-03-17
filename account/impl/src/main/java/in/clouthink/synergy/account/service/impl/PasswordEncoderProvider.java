package in.clouthink.synergy.account.service.impl;

import in.clouthink.synergy.account.AccountPasswordConfigurationProperties;
import in.clouthink.synergy.account.service.AccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderProvider implements InitializingBean {

    //initialized by InitializingBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountPasswordConfigurationProperties accountConfigurationProperties;

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.passwordEncoder = new StandardPasswordEncoder(accountConfigurationProperties.getSalt());
    }

}
