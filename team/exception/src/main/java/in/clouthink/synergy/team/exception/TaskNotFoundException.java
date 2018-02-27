package in.clouthink.synergy.team.exception;

/**
 *
 */
public class TaskNotFoundException extends TaskException {

	public TaskNotFoundException() {
	}

	public TaskNotFoundException(String message) {
		super(message);
	}

	public TaskNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskNotFoundException(Throwable cause) {
		super(cause);
	}
}
