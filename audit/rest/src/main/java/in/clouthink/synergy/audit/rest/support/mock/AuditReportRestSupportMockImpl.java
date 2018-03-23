package in.clouthink.synergy.audit.rest.support.mock;

import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.audit.rest.support.AuditReportRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
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
	public Page<AuditEventAggregation> listAuditReportByMonth(String realm, PageSearchParam queryParameter) {
		return null;
	}

	@Override
	public Page<AuditEventAggregation> listAuditReportByDay(String realm, PageSearchParam queryParameter) {
		return null;
	}
}
