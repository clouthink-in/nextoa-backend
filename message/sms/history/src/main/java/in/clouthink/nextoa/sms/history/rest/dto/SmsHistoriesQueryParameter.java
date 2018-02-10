package in.clouthink.nextoa.sms.history.rest.dto;

import in.clouthink.nextoa.shared.domain.request.impl.DateRangedQueryParameter;
import in.clouthink.nextoa.sms.history.domain.model.SmsHistory;
import in.clouthink.nextoa.sms.history.domain.request.SmsHistoryQueryRequest;

public class SmsHistoriesQueryParameter extends DateRangedQueryParameter implements SmsHistoryQueryRequest {

	private String cellphone;

	private String category;

	private SmsHistory.SmsStatus status;

	@Override
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
