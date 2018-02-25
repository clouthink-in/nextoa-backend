package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.service.DefaultResourceService;
import in.clouthink.synergy.rbac.service.ResourceService;
import in.clouthink.synergy.rbac.spi.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@ComponentScan({"in.clouthink.synergy.rbac.impl.service", "in.clouthink.synergy.rbac.impl.repository"})
@EnableMongoRepositories({"in.clouthink.synergy.rbac.impl.repository"})
public class RbacServiceModuleConfiguration {

	@Bean
	@Autowired(required = false)
	public ResourceService resourceServiceImpl(List<ResourceProvider> resourceProviderList) {
		DefaultResourceService result = new DefaultResourceService();
		result.setResourceProviderList(resourceProviderList);
		return result;
	}

}