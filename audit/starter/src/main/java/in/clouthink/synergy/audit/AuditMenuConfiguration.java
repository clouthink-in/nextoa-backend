package in.clouthink.synergy.audit;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        resource = {@Resource(code = "resource:dashboard:auditEvent",
                name = "系统操作审计",
//						  patterns = {"/api/auditEvents**", "/api/auditEvents/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"), @Action(code = "delete", name = "删除")},
                metadata = {@Metadata(key = "state", value = "dashboard.auditEvent.list")}),

                @Resource(code = "resource:dashboard:authEvent",
                        name = "系统登录审计",
//						  patterns = {"/api/authEvents**", "/api/authEvents/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"), @Action(code = "delete", name = "删除")},
                        metadata = {@Metadata(key = "state", value = "dashboard.authEvent.list")})

        })
public class AuditMenuConfiguration {
}
