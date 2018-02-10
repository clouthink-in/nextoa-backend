package in.clouthink.nextoa.menu;

import in.clouthink.nextoa.menu.core.MenuPlugin;
import in.clouthink.nextoa.menu.core.MenuResourceProvider;
import in.clouthink.nextoa.rbac.spi.ResourceProvider;
import in.clouthink.nextoa.rbac.spi.ResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenuModuleConfiguration {

	@Bean
	@Autowired(required = false)
	public ResourceProvider menuResourceProvider(List<MenuPlugin> menuPluginList) {
		return new MenuResourceProvider(menuPluginList);
	}

}
