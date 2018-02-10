package in.clouthink.nextoa.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.storage.rest.controller", "in.clouthink.nextoa.storage.rest.support.mock"})
public class StorageMockModuleConfiguration {

}
