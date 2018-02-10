package in.clouthink.nextoa.rbac;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.nextoa.rbac.rest.controller",
				"in.clouthink.nextoa.rbac.rest.service",
				"in.clouthink.nextoa.rbac.rest.support.impl"})
@Import({RbacServiceModuleConfiguration.class, RbacMenuConfiguration.class})
public class RbacRestModuleConfiguration {

}
