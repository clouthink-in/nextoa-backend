package in.clouthink.nextoa.audit.rest.support;

import in.clouthink.nextoa.audit.domain.model.AuthEventAggregation;
import in.clouthink.nextoa.shared.domain.request.impl.PageQueryParameter;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuthReportRestSupport {

	Page<AuthEventAggregation> listAuthReportByMonth(String realm, PageQueryParameter queryRequest);

	Page<AuthEventAggregation> listAuthReportByDay(String realm, PageQueryParameter queryRequest);

}
