package in.clouthink.synergy.sms;

import in.clouthink.synergy.sms.history.SmsHistoryModuleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MockSmsModuleConfiguration.class, SmsHistoryModuleConfiguration.class})
public class DummySmsRestModuleConfiguration {

}
