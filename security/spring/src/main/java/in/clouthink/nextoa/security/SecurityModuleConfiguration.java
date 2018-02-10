package in.clouthink.nextoa.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.security.impl"})
@EnableMongoRepositories({"in.clouthink.nextoa.security.impl.auth"})
public class SecurityModuleConfiguration {

}
