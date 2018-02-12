package in.clouthink.synergy.menu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.menu.rest.controller", "in.clouthink.synergy.menu.rest.support.mock"})
public class MenuMockModuleConfiguration {

}
