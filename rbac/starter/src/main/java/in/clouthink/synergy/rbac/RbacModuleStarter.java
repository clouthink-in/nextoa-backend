package in.clouthink.synergy.rbac;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ResourceCoreConfiguration.class,
        RbacServiceConfiguration.class,
        RbacRestConfiguration.class,
        RbacResourceConfiguration.class})
public class RbacModuleStarter {

}
