package in.clouthink.synergy.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.account.rest.controller", "in.clouthink.synergy.account.rest.support.impl"})
@Import({AccountServiceModuleConfiguration.class})
public class AccountRestConfiguration {

}
