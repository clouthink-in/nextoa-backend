package in.clouthink.synergy.sms.history.rest.support;

import in.clouthink.synergy.sms.history.domain.request.SmsHistorySearchRequest;
import in.clouthink.synergy.sms.history.rest.dto.SmsHistorySummary;
import org.springframework.data.domain.Page;

public interface SmsHistorySupport {

	Page<SmsHistorySummary> findPage(SmsHistorySearchRequest request);
}
