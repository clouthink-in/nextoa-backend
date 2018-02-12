package in.clouthink.synergy.menu;

import in.clouthink.synergy.menu.core.MenuPlugin;
import in.clouthink.synergy.menu.core.MenuResourceProvider;
import in.clouthink.synergy.rbac.spi.ResourceProvider;
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
