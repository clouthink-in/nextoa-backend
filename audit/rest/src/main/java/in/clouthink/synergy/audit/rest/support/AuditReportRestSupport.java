package in.clouthink.synergy.audit.rest.support;

import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuditReportRestSupport {

	Page<AuditEventAggregation> listAuditReportByMonth(String realm, PageSearchParam queryParameter);

	Page<AuditEventAggregation> listAuditReportByDay(String realm, PageSearchParam queryParameter);

}
