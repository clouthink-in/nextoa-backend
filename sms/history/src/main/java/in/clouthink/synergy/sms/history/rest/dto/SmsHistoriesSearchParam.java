package in.clouthink.synergy.sms.history.rest.dto;

import in.clouthink.synergy.shared.domain.request.impl.DateRangedSearchParam;
import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.sms.history.domain.request.SmsHistorySearchRequest;

public class SmsHistoriesSearchParam extends DateRangedSearchParam implements SmsHistorySearchRequest {

	private String telephone;

	private String category;

	private SmsHistory.SmsStatus status;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public SmsHistory.SmsStatus getStatus() {
		return status;
	}

	public void setStatus(SmsHistory.SmsStatus status) {
		this.status = status;
	}
}
