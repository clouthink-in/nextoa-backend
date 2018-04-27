package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.service.ResourceDiscovery;
import in.clouthink.synergy.rbac.spi.ResourceProvider;
import in.clouthink.synergy.rbac.support.memory.ResourceMemoryDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@ComponentScan({"in.clouthink.synergy.rbac.impl.service", "in.clouthink.synergy.rbac.impl.repository"})
@EnableMongoRepositories({"in.clouthink.synergy.rbac.impl.repository"})
public class RbacServiceConfiguration {

    @Bean
    @Autowired(required = false)
    public ResourceDiscovery resourceMemoryDiscovery(List<ResourceProvider> resourceProviderList) {
        ResourceMemoryDiscovery result = new ResourceMemoryDiscovery();
        result.setResourceProviderList(resourceProviderList);
        return result;
    }

}
