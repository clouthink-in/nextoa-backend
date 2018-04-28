package in.clouthink.synergy.setting;

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
        @Resource(
                code = "system-setting:manage",
                name = "系统设置",
                permission = {@Permission(api = "/api/system-settings", action = {Action.GET})}
        ),

        @Resource(
                code = "system-setting:edit",
                name = "修改",
                permission = {@Permission(api = "/api/system-settings", action = {Action.POST, Action.PUT})}
        ),
})
public class SystemSettingResourceConfiguration {
}
