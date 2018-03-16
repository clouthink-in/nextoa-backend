package in.clouthink.synergy.sms.history.rest.dto;


import in.clouthink.synergy.shared.domain.request.impl.DateRangedQueryParameter;
import in.clouthink.synergy.sms.history.domain.model.SmsHistory;
import in.clouthink.synergy.sms.history.domain.request.SmsHistoryQueryRequest;
import io.swagger.annotations.ApiModel;

@ApiModel
public class SmsHistoryQueryParameter extends DateRangedQueryParameter implements SmsHistoryQueryRequest {

	private String telephone;

	private String category;

	private SmsHistory.SmsStatus status;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SmsHistory.SmsStatus getStatus() {
		return status;
	}

	public void setStatus(SmsHistory.SmsStatus status) {
		this.status = status;
	}
}
