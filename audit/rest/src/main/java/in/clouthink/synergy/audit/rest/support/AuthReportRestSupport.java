package in.clouthink.synergy.audit.rest.support;

import in.clouthink.synergy.audit.domain.model.AuthEventAggregation;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuthReportRestSupport {

	Page<AuthEventAggregation> listAuthReportByMonth(String realm, PageSearchParam queryRequest);

	Page<AuthEventAggregation> listAuthReportByDay(String realm, PageSearchParam queryRequest);

}
