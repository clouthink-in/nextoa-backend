package in.clouthink.synergy.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.audit.event",
		"in.clouthink.synergy.audit.service",
		"in.clouthink.synergy.audit.repository"})
@EnableMongoRepositories({"in.clouthink.synergy.audit.repository"})
public class AuditServiceConfiguration {

}
