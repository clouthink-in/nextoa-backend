package in.clouthink.nextoa.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.audit.event",
				"in.clouthink.nextoa.audit.service",
				"in.clouthink.nextoa.audit.repository"})
@EnableMongoRepositories({"in.clouthink.nextoa.audit.repository"})
public class AuditServiceModuleConfiguration {

}
