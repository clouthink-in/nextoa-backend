package in.clouthink.synergy.sms.history.repository.custom;

import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.sms.history.domain.request.SmsHistoryQueryRequest;
import in.clouthink.synergy.shared.repository.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;

/**
 * 短信发送记录持久层扩展
 */
public interface SmsHistoryRepositoryCustom extends AbstractCustomRepository {

	Page<SmsHistory> queryPage(SmsHistoryQueryRequest parameter);

}
