package in.clouthink.synergy.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.daas.fss.rest",
        "in.clouthink.synergy.storage.controller",
        "in.clouthink.synergy.storage.support"})
public class StorageRestConfiguration {

}
