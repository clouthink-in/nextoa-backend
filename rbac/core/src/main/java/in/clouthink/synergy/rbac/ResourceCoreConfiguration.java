package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.service.ResourceRegistry;
import in.clouthink.synergy.rbac.support.memory.ResourceMemoryRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceCoreConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResourceRegistry.class)
    public ResourceRegistry resourceMemoryRegistry() {
        return new ResourceMemoryRegistry();
    }

}
