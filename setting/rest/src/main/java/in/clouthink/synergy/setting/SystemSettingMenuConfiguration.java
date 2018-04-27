package in.clouthink.synergy.setting;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        resource = {@Resource(code = "resource:dashboard:setting",
                name = "系统设置",
//						  patterns = {"/api/settings/system**", "/api/settings/system/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"), @Action(code = "update", name = "修改")},
                metadata = {@Metadata(key = "state", value = "dashboard.systemSetting.list")})})
public class SystemSettingMenuConfiguration {
}
