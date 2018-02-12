package in.clouthink.synergy.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.account.rest.controller", "in.clouthink.synergy.account.rest.support.mock"})
public class AccountMockModuleConfiguration {

}
