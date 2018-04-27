package in.clouthink.synergy.account;

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
                code = "user:retrieve",
                name = "查看通讯录",
                permission = {
                        @Permission(api = "/api/contacts**", action = {Action.GET})
                }),

        @Resource(
                code = "user:retrieve",
                name = "查看系统用户",
                permission = {
                        @Permission(api = "/api/users**", action = {Action.GET})
                }),

        @Resource(
                parent = "user:retrieve",
                code = "user:update",
                name = "编辑用户",
                permission = {
                        @Permission(api = "/api/users**", action = {Action.POST})
                }),

        @Resource(
                parent = "user:retrieve",
                code = "user:delete",
                name = "删除用户",
                permission = {
                        @Permission(api = "/api/users**", action = {Action.DELETE})
                }),

        @Resource(
                parent = "user:retrieve",
                code = "user:change-pwd",
                name = "修改用户密码",
                permission = {
                        @Permission(api = "/api/users/*/password", action = {Action.POST})
                }),

        @Resource(
                code = "archived-user:retrieve",
                name = "查看归档用户",
                permission = {
                        @Permission(api = "/api/archived-users**", action = {Action.GET})
                }),

        @Resource(
                code = "role:retrieve",
                name = "查看角色",
                permission = {
                        @Permission(api = "/api/roles**", action = {Action.GET})
                }),

        @Resource(
                parent = "role:retrieve",
                code = "role:update",
                name = "编辑角色",
                permission = {
                        @Permission(api = "/api/roles**", action = {Action.POST})
                }),

        @Resource(
                parent = "role:retrieve",
                code = "role:delete",
                name = "删除角色",
                permission = {
                        @Permission(api = "/api/roles**", action = {Action.DELETE})
                }),

        @Resource(
                parent = "role:retrieve",
                code = "role:bind-user",
                name = "绑定用户",
                permission = {
                        @Permission(api = "/api/roles/*/bind-users", action = {Action.POST})
                }),

        @Resource(
                parent = "role:retrieve",
                code = "role:unbind-user",
                name = "取消绑定用户",
                permission = {
                        @Permission(api = "/api/roles/*/unbind-users", action = {Action.POST})
                }),


        @Resource(
                code = "group:retrieve",
                name = "查看用户组",
                permission = {
                        @Permission(api = "/api/groups**", action = {Action.GET})
                }),

        @Resource(
                parent = "group:retrieve",
                code = "group:update",
                name = "编辑用户组",
                permission = {
                        @Permission(api = "/api/groups**", action = {Action.POST})
                }),

        @Resource(
                parent = "group:retrieve",
                code = "group:delete",
                name = "删除用户组",
                permission = {
                        @Permission(api = "/api/groups**", action = {Action.DELETE})
                }),

        @Resource(
                parent = "group:retrieve",
                code = "group:bind-user",
                name = "绑定用户",
                permission = {
                        @Permission(api = "/api/groups/*/bind-users", action = {Action.POST})
                }),

        @Resource(
                parent = "group:retrieve",
                code = "group:unbind-user",
                name = "取消绑定用户",
                permission = {
                        @Permission(api = "/api/groups/*/unbind-users", action = {Action.POST})
                }),

})
public class AccountResourceConfiguration {

}
