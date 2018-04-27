package in.clouthink.synergy.account;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        resource = {@Resource(code = "resource:dashboard:sysuser",
                name = "系统用户",
//						  patterns = {"/api/sysusers**", "/api/sysusers/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "create", name = "新增"),
//									 @Action(code = "update", name = "修改"),
//									 @Action(code = "delete", name = "删除"),
//									 @Action(code = "password", name = "修改密码")},
                metadata = {@Metadata(key = "state", value = "dashboard.sysuser.list")}),

                @Resource(code = "resource:dashboard:archiveduser",
                        name = "归档用户",
//						  patterns = {"/api/archivedusers**", "/api/archivedusers/**"},
//						  actions = {@Action(code = "retrieve", name = "查看")},
                        metadata = {@Metadata(key = "state", value = "dashboard.archiveduser.list")}),

                @Resource(code = "resource:dashboard:sysrole",
                        name = "内置角色管理",
//						  patterns = {"/api/roles/sysroles**", "/api/roles/sysroles/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "binduser", name = "绑定用户"),
//									 @Action(code = "unbinduser", name = "取消绑定用户")},
                        metadata = {@Metadata(key = "state", value = "dashboard.sysrole.list")}),

                @Resource(code = "resource:dashboard:extrole",
                        name = "扩展角色管理",
//						  patterns = {"/api/roles/extroles**", "/api/roles/extroles/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "create", name = "新增"),
//									 @Action(code = "update", name = "修改"),
//									 @Action(code = "delete", name = "删除"),
//									 @Action(code = "binduser", name = "绑定用户"),
//									 @Action(code = "unbinduser", name = "取消绑定用户")},
                        metadata = {@Metadata(key = "state", value = "dashboard.extrole.list")})

        })
public class AccountMenuConfiguration {

}
