package in.clouthink.synergy.shared.domain.request.impl;


import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel
public class DateRangedSearchParam extends PageSearchParam implements DateRangedSearchRequest {

	private Date beginDate;

	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
