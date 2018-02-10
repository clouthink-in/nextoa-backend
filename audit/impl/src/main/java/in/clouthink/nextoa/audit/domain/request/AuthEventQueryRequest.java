package in.clouthink.nextoa.audit.domain.request;

import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 * @author dz
 */
public interface AuthEventQueryRequest extends DateRangedQueryRequest {

	/**
	 * @return never null
	 */
	String getRealm();

	/**
	 * @return who request the authentication
	 */
	String getUsername();

	/**
	 * @return
	 */
	Boolean getSucceed();

}
