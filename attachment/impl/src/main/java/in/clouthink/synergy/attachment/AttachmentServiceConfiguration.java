package in.clouthink.synergy.attachment;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.attachment.service", "in.clouthink.synergy.attachment.event"})
@EnableMongoRepositories({"in.clouthink.synergy.attachment.repository"})
@EnableConfigurationProperties(AttachmentConfigurationProperties.class)
public class AttachmentServiceConfiguration {

}
