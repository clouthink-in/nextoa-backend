package in.clouthink.synergy.rbac;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.rbac.rest.controller", "in.clouthink.synergy.rbac.rest.support.mock"})
public class RbacMockConfiguration {

}
