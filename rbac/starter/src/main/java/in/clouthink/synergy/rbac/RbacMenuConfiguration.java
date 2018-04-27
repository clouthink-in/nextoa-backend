package in.clouthink.synergy.rbac;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import in.clouthink.synergy.rbac.annotation.Resource;
import org.springframework.context.annotation.Configuration;

/**
 * @author dz
 */
@Configuration
@EnableResource(
        resource = {@Resource(code = "resource:dashboard:permission:ext",
                name = "权限管理",
//						  patterns = {"/api/permissions/ext**", "/api/permissions/ext/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "grant", name = "授权"),
//									 @Action(code = "revoke", name = "取消授权")},
                metadata = {@Metadata(key = "state", value = "dashboard.permission.ext.list")}),

                @Resource(code = "resource:dashboard:permission:sys",
                        name = "内置权限管理",
//						  patterns = {"/api/permissions/sys**", "/api/permissions/sys/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "grant", name = "授权"),
//									 @Action(code = "revoke", name = "取消授权")},
                        metadata = {@Metadata(key = "state", value = "dashboard.permission.sys.list")}),

        })
public class RbacMenuConfiguration {

}
