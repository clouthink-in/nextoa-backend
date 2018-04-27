package in.clouthink.synergy.sms.history;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.sms.history.repository",
		"in.clouthink.synergy.sms.history.event",
		"in.clouthink.synergy.sms.history.service",
		"in.clouthink.synergy.sms.history.rest.controller",
		"in.clouthink.synergy.sms.history.rest.support.impl"})
@EnableMongoRepositories({"in.clouthink.synergy.sms.history.repository"})
@Import({SmsHistoryResourceConfiguration.class})
public class SmsHistoryModuleConfiguration {

}
