package in.clouthink.synergy.sms.history.domain.request;

import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;

/**
 */
public interface SmsHistorySearchRequest extends DateRangedSearchRequest {

	String getTelephone();

	String getCategory();

	SmsHistory.SmsStatus getStatus();

}
