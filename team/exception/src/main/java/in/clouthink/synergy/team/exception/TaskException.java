package in.clouthink.synergy.team.exception;

/**
 *
 */
public class TaskException extends TeamException {

	public TaskException() {
	}

	public TaskException(String message) {
		super(message);
	}

	public TaskException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskException(Throwable cause) {
		super(cause);
	}
}
