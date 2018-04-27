package in.clouthink.synergy.audit;

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
                code = "audit-event:retrieve",
                name = "查看系统操作日志",
                permission = {@Permission(api = "/api/auditEvents**", action = {Action.GET})}),
        @Resource(
                parent = "audit-event:retrieve",
                code = "audit-event:delete",
                name = "删除系统操作日志",
                permission = {@Permission(api = "/api/auditEvents**", action = {Action.DELETE})}),
        @Resource(
                code = "auth-event:retrieve",
                name = "查看系统登录日志",
                permission = {@Permission(api = "/api/authEvents**", action = {Action.GET})}),
        @Resource(
                parent = "auth-event:retrieve",
                code = "auth-event:delete",
                name = "删除系统登录日志",
                permission = {@Permission(api = "/api/authEvents**", action = {Action.DELETE})})
})
public class AuditResourceConfiguration {
}
