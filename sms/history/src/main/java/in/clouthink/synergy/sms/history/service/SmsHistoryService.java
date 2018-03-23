package in.clouthink.synergy.sms.history.service;

import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.sms.history.domain.request.SmsHistorySearchRequest;
import org.springframework.data.domain.Page;

public interface SmsHistoryService {

	Page<SmsHistory> findPage(SmsHistorySearchRequest request);

	SmsHistory save(SmsHistory smsHistory);

}
