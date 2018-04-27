package in.clouthink.synergy.rbac.exception;

/**
 * permission exception
 */
public class NoAccessPermissionException extends RuntimeException {

	public NoAccessPermissionException() {
	}

	public NoAccessPermissionException(String message) {
		super(message);
	}

	public NoAccessPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAccessPermissionException(Throwable cause) {
		super(cause);
	}

}
