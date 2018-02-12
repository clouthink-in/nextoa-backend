package in.clouthink.synergy.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.storage.rest.controller", "in.clouthink.synergy.storage.rest.support.mock"})
public class StorageMockModuleConfiguration {

}
