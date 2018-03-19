package in.clouthink.synergy.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.audit.rest.controller", "in.clouthink.synergy.audit.rest.support.impl"})
public class AuditRestConfiguration {

}
