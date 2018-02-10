package in.clouthink.nextoa.account;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.nextoa.account.rest.controller", "in.clouthink.nextoa.account.rest.support.impl"})
@Import({AccountServiceModuleConfiguration.class, AccountMenuConfiguration.class})
@EnableConfigurationProperties(AdministratorAccountProperties.class)
public class AccountRestModuleConfiguration {

    @Bean
    @ConditionalOnProperty(name = "in.clouthink.nextoa.account.administrator.username")
    public AccountInitializingBean accountInitializingBean() {
        return new AccountInitializingBean();
    }

}
