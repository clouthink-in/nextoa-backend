package in.clouthink.synergy.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.daas.fss.rest",
		"in.clouthink.synergy.storage.rest.controller",
		"in.clouthink.synergy.storage.rest.support.impl"})
@Import({GridfsModuleConfiguration.class})
public class StorageModuleConfiguration {

}
