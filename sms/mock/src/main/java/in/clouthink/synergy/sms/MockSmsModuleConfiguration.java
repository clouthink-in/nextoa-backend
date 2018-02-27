package in.clouthink.synergy.sms;

import in.clouthink.daas.edm.EventListener;
import in.clouthink.synergy.sms.mock.PasscodeMessageEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockSmsModuleConfiguration {

	@Bean
	public EventListener passcodeMessageEventListener() {
		return new PasscodeMessageEventListener();
	}

}
