package in.clouthink.nextoa.menu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.nextoa.menu.rest.controller", "in.clouthink.nextoa.menu.rest.support.impl"})
@Import({MenuModuleConfiguration.class, GlobalMenuConfiguration.class})
public class MenuRestModuleConfiguration {

}
