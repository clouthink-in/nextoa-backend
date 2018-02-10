package in.clouthink.nextoa.sms.history;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.sms.history.rest.controller",
				"in.clouthink.nextoa.sms.history.rest.support.mock"})
public class SmsHistoryMockModuleConfiguration {

}
