package in.clouthink.nextoa.sms;

import in.clouthink.nextoa.sms.history.SmsHistoryModuleConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AliyunSmsModuleConfiguration.class, SmsHistoryModuleConfiguration.class})
public class SmsRestModuleConfiguration {

}
