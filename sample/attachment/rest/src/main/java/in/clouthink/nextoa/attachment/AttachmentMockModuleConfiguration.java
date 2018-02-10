package in.clouthink.nextoa.attachment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.attachment.rest.controller",
				"in.clouthink.nextoa.attachment.rest.support.mock"})
public class AttachmentMockModuleConfiguration {

}
