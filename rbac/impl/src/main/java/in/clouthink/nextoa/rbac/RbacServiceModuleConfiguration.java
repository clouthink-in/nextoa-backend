package in.clouthink.nextoa.rbac;

import in.clouthink.nextoa.rbac.service.DefaultResourceService;
import in.clouthink.nextoa.rbac.service.ResourceService;
import in.clouthink.nextoa.rbac.spi.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@ComponentScan({"in.clouthink.nextoa.rbac.impl.service", "in.clouthink.nextoa.rbac.impl.repository"})
@EnableMongoRepositories({"in.clouthink.nextoa.rbac.impl.repository"})
public class RbacServiceModuleConfiguration {

	@Bean
	@Autowired(required = false)
	public ResourceService resourceServiceImpl(List<ResourceProvider> resourceProviderList) {
		DefaultResourceService result = new DefaultResourceService();
		result.setResourceProviderList(resourceProviderList);
		return result;
	}

}
