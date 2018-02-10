package in.clouthink.nextoa.account.exception;

import in.clouthink.nextoa.exception.BizException;

/**
 *
 */
public class UserException extends BizException {

	public UserException() {
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
}
