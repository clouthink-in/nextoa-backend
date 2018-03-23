package in.clouthink.synergy.audit.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;

/**
 * @author dz
 */
public interface AuthEventSearchRequest extends DateRangedSearchRequest {

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
