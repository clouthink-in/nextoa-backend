package in.clouthink.nextoa.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.account.rest.controller", "in.clouthink.nextoa.account.rest.support.mock"})
public class AccountMockModuleConfiguration {

}
