package in.clouthink.synergy.audit.rest.support.mock;

import in.clouthink.synergy.audit.domain.model.AuthEventAggregation;
import in.clouthink.synergy.audit.rest.support.AuthReportRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * AuthReportRestSupport mocker
 *
 * @author dz
 */
@Component
public class AuthReportRestSupportMockImpl implements AuthReportRestSupport {
	@Override
	public Page<AuthEventAggregation> listAuthReportByMonth(String realm, PageSearchParam queryRequest) {
		return null;
	}

	@Override
	public Page<AuthEventAggregation> listAuthReportByDay(String realm, PageSearchParam queryRequest) {
		return null;
	}
}
