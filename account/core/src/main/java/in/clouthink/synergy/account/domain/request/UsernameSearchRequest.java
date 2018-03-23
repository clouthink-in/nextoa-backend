package in.clouthink.synergy.account.domain.request;


import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;

public interface UsernameSearchRequest extends DateRangedSearchRequest {

	/**
	 * 用户名
	 *
	 * @return
	 */
	String getUsername();

}
