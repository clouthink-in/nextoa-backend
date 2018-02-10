package in.clouthink.nextoa.audit.rest.support.mock;

import in.clouthink.nextoa.audit.domain.model.AuditEventAggregation;
import in.clouthink.nextoa.audit.rest.support.AuditReportRestSupport;
import in.clouthink.nextoa.shared.domain.request.impl.PageQueryParameter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * AuditReportRestSupport mocker
 *
 * @author dz
 */
@Component
public class AuditReportRestSupportMockImpl implements AuditReportRestSupport {
	@Override
	public Page<AuditEventAggregation> listAuditReportByMonth(String realm, PageQueryParameter queryParameter) {
		return null;
	}

	@Override
	public Page<AuditEventAggregation> listAuditReportByDay(String realm, PageQueryParameter queryParameter) {
		return null;
	}
}
