package in.clouthink.synergy.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.daas.fss.rest",
        "in.clouthink.synergy.storage.mock"})
public class StorageMockConfiguration {

}
