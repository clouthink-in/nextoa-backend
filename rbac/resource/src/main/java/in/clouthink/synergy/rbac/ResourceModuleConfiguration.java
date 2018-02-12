package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.repository.ResourceMemoryRepository;
import in.clouthink.synergy.rbac.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceModuleConfiguration {

	@Bean
	@Autowired
	public ResourceRepository resourceServiceImpl() {
		return new ResourceMemoryRepository();
	}

}
