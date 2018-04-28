package in.clouthink.synergy.sms.history;

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
        @Resource(code = "sms:manage",
                name = "短信发送记录",
                permission = {
                        @Permission(api = "/api/sms-histories**", action = {Action.GET})
                })
})
public class SmsHistoryResourceConfiguration {
}
