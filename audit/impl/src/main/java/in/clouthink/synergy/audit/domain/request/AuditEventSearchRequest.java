package in.clouthink.synergy.audit.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;

/**
 * @author dz
 */
public interface AuditEventSearchRequest extends DateRangedSearchRequest {

	/**
	 * @return never null
	 */
	String getRealm();

	/**
	 * @return who send the request
	 */
	String getRequestedBy();

	/**
	 * @return
	 */
	String getRequestedUrl();

	/**
	 * @return
	 */
	Boolean getError();

	/**
	 * @return
	 */
	String getClientAddress();

	/**
	 * @return
	 */
	String getServiceName();

	/**
	 * @return
	 */
	String getMethodName();

	/**
	 * @return
	 */
	Long getTimeCost();

}
