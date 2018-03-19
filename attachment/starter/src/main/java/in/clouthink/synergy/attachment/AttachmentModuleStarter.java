package in.clouthink.synergy.attachment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AttachmentServiceConfiguration.class, AttachmentRestConfiguration.class, AttachmentMenuConfiguration.class})
public class AttachmentModuleStarter {

}
