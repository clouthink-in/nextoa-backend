package in.clouthink.synergy.menu;

import in.clouthink.synergy.rbac.annotation.EnableMenu;
import in.clouthink.synergy.rbac.annotation.ExtensionPoint;
import in.clouthink.synergy.rbac.annotation.Menu;
import in.clouthink.synergy.rbac.annotation.Metadata;
import in.clouthink.synergy.rbac.config.core.Menus;
import org.springframework.context.annotation.Configuration;

/**
 * Show two ways to declare the menu plugin (extend the root extension point and declare new sub-menu)
 *
 * @author dz
 */
@Configuration
@EnableMenu(pluginId = "plugin:menu:sample",
        extensionPointId = Menus.ROOT_EXTENSION_POINT_ID,
        menu = {@Menu(code = "menu:dashboard:sample",
                name = "模块示例",
                order = 100,
                metadata = {@Metadata(key = "icon", value = "fa fa-gear")},
                extensionPoint = {@ExtensionPoint(id = "extension:menu:sample")}),

                @Menu(code = "menu:dashboard:system",
                        name = "系统管理",
                        order = 200,
                        metadata = {@Metadata(key = "icon", value = "fa fa-gear")},
                        extensionPoint = {@ExtensionPoint(id = "extension:menu:system")}),

        })
public class GlobalMenuConfiguration {

}
