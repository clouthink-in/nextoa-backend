package in.clouthink.synergy.account;

import in.clouthink.synergy.account.initialize.AccountInitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @auther dz
 */
@Import({AccountServiceConfiguration.class, AccountRestConfiguration.class, AccountMenuConfiguration.class})
@EnableConfigurationProperties(AccountAdministratorProperties.class)
public class AccountModuleStarter {

    @Bean
    @ConditionalOnProperty(name = "synergy.account.administrator.username")
    public AccountInitializingBean accountInitializingBean() {
        return new AccountInitializingBean();
    }

}
