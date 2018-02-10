package in.clouthink.nextoa.setting;

import in.clouthink.nextoa.menu.annotation.Action;
import in.clouthink.nextoa.menu.annotation.EnableMenu;
import in.clouthink.nextoa.menu.annotation.Menu;
import in.clouthink.nextoa.menu.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableMenu(pluginId = "plugin:menu:setting",
			extensionPointId = "extension:menu:system",
			menu = {@Menu(code = "menu:dashboard:setting",
						  name = "系统设置",
						  order = 2020,
						  patterns = {"/api/settings/system**", "/api/settings/system/**"},
						  actions = {@Action(code = "retrieve", name = "查看"), @Action(code = "update", name = "修改")},
						  metadata = {@Metadata(key = "state", value = "dashboard.systemSetting.list")})})
public class SystemSettingMenuConfiguration {
}
