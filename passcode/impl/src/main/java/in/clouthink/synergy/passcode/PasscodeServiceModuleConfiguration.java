package in.clouthink.synergy.passcode;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.passcode.service",
		"in.clouthink.synergy.passcode.event",
		"in.clouthink.synergy.passcode.engine"})
@EnableMongoRepositories({"in.clouthink.synergy.passcode.repository"})
@EnableConfigurationProperties(PasscodeConfigurationProperties.class)
public class PasscodeServiceModuleConfiguration {

}
