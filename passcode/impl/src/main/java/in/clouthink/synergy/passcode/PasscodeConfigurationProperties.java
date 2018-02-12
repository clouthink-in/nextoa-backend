package in.clouthink.synergy.passcode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "synergy.passcode")
public class PasscodeConfigurationProperties {

}
