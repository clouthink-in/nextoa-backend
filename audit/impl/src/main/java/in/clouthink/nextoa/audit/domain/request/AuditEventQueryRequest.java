package in.clouthink.nextoa.audit.domain.request;

import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 * @author dz
 */
public interface AuditEventQueryRequest extends DateRangedQueryRequest {

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
