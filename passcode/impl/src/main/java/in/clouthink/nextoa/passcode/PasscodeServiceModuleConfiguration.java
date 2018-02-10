package in.clouthink.nextoa.passcode;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.passcode.service",
				"in.clouthink.nextoa.passcode.event",
				"in.clouthink.nextoa.passcode.engine"})
@EnableMongoRepositories({"in.clouthink.nextoa.passcode.repository"})
@EnableConfigurationProperties(PasscodeConfigurationProperties.class)
public class PasscodeServiceModuleConfiguration {

}
