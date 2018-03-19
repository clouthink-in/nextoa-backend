package in.clouthink.synergy.team;

import in.clouthink.synergy.menu.annotation.Action;
import in.clouthink.synergy.menu.annotation.EnableMenu;
import in.clouthink.synergy.menu.annotation.Menu;
import in.clouthink.synergy.menu.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableMenu(pluginId = "plugin:menu:team",
        extensionPointId = "extension:menu:sample",
        menu = {
                @Menu(code = "menu:dashboard:team:activity",
                        name = "协作管理",
                        order = 1001,
                        patterns = {"/api/activities**", "/api/activities/**"},
                        actions = {@Action(code = "retrieve", name = "查看")},
                        metadata = {@Metadata(key = "state", value = "dashboard.team.activity.list")}),

                @Menu(code = "menu:dashboard:team:task",
                        name = "任务管理",
                        order = 1002,
                        patterns = {"/api/tasks**", "/api/tasks/**"},
                        actions = {@Action(code = "retrieve", name = "查看")},
                        metadata = {@Metadata(key = "state", value = "dashboard.team.task.list")}),
        })
public class TeamMenuConfiguration {

}
