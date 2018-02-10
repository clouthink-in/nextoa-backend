package in.clouthink.nextoa.rbac;

import in.clouthink.nextoa.rbac.repository.ResourceMemoryRepository;
import in.clouthink.nextoa.rbac.repository.ResourceRepository;
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
