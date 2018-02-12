package in.clouthink.synergy.sms.history;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.sms.history.rest.controller",
		"in.clouthink.synergy.sms.history.rest.support.mock"})
public class SmsHistoryMockModuleConfiguration {

}
