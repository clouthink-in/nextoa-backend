package in.clouthink.synergy.sms.history.domain.request;

import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.shared.domain.request.DateRangedQueryRequest;

/**
 */
public interface SmsHistoryQueryRequest extends DateRangedQueryRequest {

	String getCellphone();

	String getCategory();

	SmsHistory.SmsStatus getStatus();

}
