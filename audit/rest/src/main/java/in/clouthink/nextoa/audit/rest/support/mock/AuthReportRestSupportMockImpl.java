package in.clouthink.nextoa.audit.rest.support.mock;

import in.clouthink.nextoa.audit.domain.model.AuthEventAggregation;
import in.clouthink.nextoa.audit.rest.support.AuthReportRestSupport;
import in.clouthink.nextoa.shared.domain.request.impl.PageQueryParameter;
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
	public Page<AuthEventAggregation> listAuthReportByMonth(String realm, PageQueryParameter queryRequest) {
		return null;
	}

	@Override
	public Page<AuthEventAggregation> listAuthReportByDay(String realm, PageQueryParameter queryRequest) {
		return null;
	}
}
