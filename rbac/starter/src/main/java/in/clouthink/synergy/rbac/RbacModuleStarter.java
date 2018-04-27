package in.clouthink.synergy.rbac;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RbacServiceConfiguration.class,
        RbacRestConfiguration.class,
        RbacResourceConfiguration.class})
public class RbacModuleStarter {

}
