package in.clouthink.daas.sbb.dashboard;

import in.clouthink.daas.plugin.annotation.Plugin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Plugin(extensionPoints= {})
@ComponentScan({"in.clouthink.daas.sbb.dashboard.rest", "in.clouthink.daas.fss"})
public class AccountRestApiModuleConfiguration {

}