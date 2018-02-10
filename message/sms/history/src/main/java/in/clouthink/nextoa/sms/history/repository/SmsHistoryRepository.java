package in.clouthink.nextoa.sms.history.repository;

import in.clouthink.nextoa.sms.history.domain.model.SmsHistory;
import in.clouthink.nextoa.sms.history.repository.custom.SmsHistoryRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dz
 */
public interface SmsHistoryRepository extends AbstractRepository<SmsHistory>, SmsHistoryRepositoryCustom {

	Page<SmsHistory> findByCategory(String category, Pageable pageable);

}
