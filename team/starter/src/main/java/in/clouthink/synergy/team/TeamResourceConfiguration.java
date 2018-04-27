package in.clouthink.synergy.team;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Permission;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import in.clouthink.synergy.rbac.model.Action;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource({
        @Resource(code = "team:activity:manage",
                name = "协作管理",
                permission = {
                        @Permission(api = "/api/activities**", action = {Action.GET, Action.POST, Action.PUT, Action.DELETE})
                }),

        @Resource(code = "team:task:manage",
                name = "任务管理",
                permission = {
                        @Permission(api = "/api/tasks**", action = {Action.GET, Action.POST, Action.PUT, Action.DELETE})
                }),

        @Resource(code = "team:supervised-activity:manage",
                name = "协作库管理",
                permission = {
                        @Permission(api = "/api/supervised-activity**", action = {Action.GET, Action.POST, Action.PUT, Action.DELETE})
                }),

})
public class TeamResourceConfiguration {

}
