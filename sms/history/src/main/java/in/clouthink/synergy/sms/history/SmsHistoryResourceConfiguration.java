package in.clouthink.synergy.sms.history;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        value = {@Resource(code = "value:dashboard:sms",
                name = "短信发送记录",
//						  patterns = {"/api/smsHistories**", "/api/smsHistories/**"},
//						  actions = {@Action(code = "retrieve", name = "查看")},
                metadata = {@Metadata(key = "state", value = "dashboard.smsHistory.list")})})
public class SmsHistoryResourceConfiguration {
}
