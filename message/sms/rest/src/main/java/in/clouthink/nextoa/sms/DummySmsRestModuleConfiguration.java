package in.clouthink.nextoa.sms;

import in.clouthink.nextoa.sms.history.SmsHistoryModuleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MockSmsModuleConfiguration.class, SmsHistoryModuleConfiguration.class})
public class DummySmsRestModuleConfiguration {

}
