package in.clouthink.nextoa.rbac;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.rbac.rest.controller", "in.clouthink.nextoa.rbac.rest.support.mock"})
public class RbacMockModuleConfiguration {

}
