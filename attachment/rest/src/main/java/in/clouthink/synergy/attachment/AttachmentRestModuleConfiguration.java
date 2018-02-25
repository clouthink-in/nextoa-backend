package in.clouthink.synergy.attachment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.attachment.rest.controller",
		"in.clouthink.synergy.attachment.rest.support.impl"})
@Import({AttachmentServiceModuleConfiguration.class, AttachmentMenuConfiguration.class})
public class AttachmentRestModuleConfiguration {

}
