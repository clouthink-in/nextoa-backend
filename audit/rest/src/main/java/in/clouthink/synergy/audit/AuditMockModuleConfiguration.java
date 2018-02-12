package in.clouthink.synergy.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.audit.rest.controller", "in.clouthink.synergy.audit.rest.support.mock"})
public class AuditMockModuleConfiguration {

}
