package in.clouthink.nextoa.attachment;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.attachment.service", "in.clouthink.nextoa.attachment.event"})
@EnableMongoRepositories({"in.clouthink.nextoa.attachment.repository"})
@EnableConfigurationProperties(AttachmentConfigurationProperties.class)
public class AttachmentServiceModuleConfiguration {

}
