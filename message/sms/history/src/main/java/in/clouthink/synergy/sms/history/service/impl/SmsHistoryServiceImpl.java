package in.clouthink.synergy.sms.history.service.impl;

import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.sms.history.domain.request.SmsHistoryQueryRequest;
import in.clouthink.synergy.sms.history.repository.SmsHistoryRepository;
import in.clouthink.synergy.sms.history.service.SmsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SmsHistoryServiceImpl implements SmsHistoryService {

	@Autowired
	private SmsHistoryRepository repository;

	@Override
	public Page<SmsHistory> findPage(SmsHistoryQueryRequest request) {
		return repository.queryPage(request);
	}

	@Override
	public SmsHistory save(SmsHistory smsHistory) {
		return repository.save(smsHistory);
	}
}
