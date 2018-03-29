package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.service.ResourceMemoryRepository;
import in.clouthink.synergy.rbac.service.ResourceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceCoreConfiguration {

    @Bean
    @ConditionalOnMissingBean(ResourceRepository.class)
    public ResourceRepository resourceServiceImpl() {
        return new ResourceMemoryRepository();
    }

}
