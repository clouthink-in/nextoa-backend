package in.clouthink.synergy.rbac;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.rbac.rest.controller",
		"in.clouthink.synergy.rbac.rest.service",
		"in.clouthink.synergy.rbac.rest.support.impl"})
@Import({RbacServiceModuleConfiguration.class, RbacMenuConfiguration.class})
public class RbacRestModuleConfiguration {

}
