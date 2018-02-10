package in.clouthink.nextoa.sms.history.repository.custom;

import in.clouthink.nextoa.sms.history.domain.model.SmsHistory;
import in.clouthink.nextoa.sms.history.domain.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.shared.repository.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;

/**
 * 短信发送记录持久层扩展
 */
public interface SmsHistoryRepositoryCustom extends AbstractCustomRepository {

	Page<SmsHistory> queryPage(SmsHistoryQueryRequest parameter);

}
