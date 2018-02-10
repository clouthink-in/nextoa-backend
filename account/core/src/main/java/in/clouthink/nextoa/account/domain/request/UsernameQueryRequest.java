package in.clouthink.nextoa.account.domain.request;


import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

public interface UsernameQueryRequest extends DateRangedQueryRequest {

	/**
	 * 用户名
	 *
	 * @return
	 */
	String getUsername();

}
