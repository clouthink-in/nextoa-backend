package in.clouthink.nextoa.account.exception;

import in.clouthink.nextoa.exception.BizException;

/**
 *
 */
public class RoleException extends BizException {

	public RoleException() {
	}

	public RoleException(String message) {
		super(message);
	}

	public RoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleException(Throwable cause) {
		super(cause);
	}
}
