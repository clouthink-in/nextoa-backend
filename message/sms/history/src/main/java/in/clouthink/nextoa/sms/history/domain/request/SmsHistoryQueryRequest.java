package in.clouthink.nextoa.sms.history.domain.request;

import in.clouthink.nextoa.sms.history.domain.model.SmsHistory;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 */
public interface SmsHistoryQueryRequest extends DateRangedQueryRequest {

	String getCellphone();

	String getCategory();

	SmsHistory.SmsStatus getStatus();

}
