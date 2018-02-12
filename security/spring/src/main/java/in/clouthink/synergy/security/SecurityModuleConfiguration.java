package in.clouthink.synergy.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.security.impl"})
@EnableMongoRepositories({"in.clouthink.synergy.security.impl.auth"})
public class SecurityModuleConfiguration {

}
