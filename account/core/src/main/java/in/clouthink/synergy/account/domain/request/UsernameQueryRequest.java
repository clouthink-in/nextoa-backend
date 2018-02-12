package in.clouthink.synergy.account.domain.request;


import in.clouthink.synergy.shared.domain.request.DateRangedQueryRequest;

public interface UsernameQueryRequest extends DateRangedQueryRequest {

	/**
	 * 用户名
	 *
	 * @return
	 */
	String getUsername();

}
