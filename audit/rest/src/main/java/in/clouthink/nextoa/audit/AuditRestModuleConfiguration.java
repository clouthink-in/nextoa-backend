package in.clouthink.nextoa.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.nextoa.audit.rest.controller", "in.clouthink.nextoa.audit.rest.support.impl"})
@Import({AuditServiceModuleConfiguration.class, AuditMenuConfiguration.class})
public class AuditRestModuleConfiguration {

}
