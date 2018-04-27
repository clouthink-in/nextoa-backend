package in.clouthink.synergy.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AuditServiceConfiguration.class, AuditRestConfiguration.class, AuditResourceConfiguration.class})
public class AuditModuleStarter {

}
