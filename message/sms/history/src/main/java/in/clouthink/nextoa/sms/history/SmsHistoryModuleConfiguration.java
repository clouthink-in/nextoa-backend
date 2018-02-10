package in.clouthink.nextoa.sms.history;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.sms.history.repository",
				"in.clouthink.nextoa.sms.history.event",
				"in.clouthink.nextoa.sms.history.service",
				"in.clouthink.nextoa.sms.history.rest.controller",
				"in.clouthink.nextoa.sms.history.rest.support.impl"})
@EnableMongoRepositories({"in.clouthink.nextoa.sms.history.repository"})
@Import({SmsHistoryMenuConfiguration.class})
public class SmsHistoryModuleConfiguration {

}
