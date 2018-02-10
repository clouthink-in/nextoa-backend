package in.clouthink.nextoa.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.audit.rest.controller", "in.clouthink.nextoa.audit.rest.support.mock"})
public class AuditMockModuleConfiguration {

}
