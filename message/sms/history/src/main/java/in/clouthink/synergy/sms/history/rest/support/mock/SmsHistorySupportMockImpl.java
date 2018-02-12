package in.clouthink.synergy.sms.history.rest.support.mock;

import in.clouthink.synergy.sms.history.domain.request.SmsHistoryQueryRequest;
import in.clouthink.synergy.sms.history.rest.dto.SmsHistorySummary;
import in.clouthink.synergy.sms.history.rest.support.SmsHistorySupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * SmsHistorySupport mocker
 *
 * @author dz
 */
@Component
public class SmsHistorySupportMockImpl implements SmsHistorySupport {

	@Override
	public Page<SmsHistorySummary> findPage(SmsHistoryQueryRequest request) {
		return null;
	}
}
