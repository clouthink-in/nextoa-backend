package in.clouthink.nextoa.menu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.menu.rest.controller", "in.clouthink.nextoa.menu.rest.support.mock"})
public class MenuMockModuleConfiguration {

}
