package in.clouthink.synergy.account.exception;

import in.clouthink.synergy.exception.BizException;

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
