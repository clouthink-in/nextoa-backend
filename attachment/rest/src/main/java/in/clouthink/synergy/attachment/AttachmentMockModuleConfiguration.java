package in.clouthink.synergy.attachment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.attachment.rest.controller",
		"in.clouthink.synergy.attachment.rest.support.mock"})
public class AttachmentMockModuleConfiguration {

}
