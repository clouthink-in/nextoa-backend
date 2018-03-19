package in.clouthink.synergy.menu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.menu.rest.controller", "in.clouthink.synergy.menu.rest.support.impl"})
public class MenuRestConfiguration {

}
