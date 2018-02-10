package in.clouthink.nextoa.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.daas.fss.rest",
				"in.clouthink.nextoa.storage.rest.controller",
				"in.clouthink.nextoa.storage.rest.support.impl"})
@Import({GridfsModuleConfiguration.class})
public class StorageModuleConfiguration {

}
