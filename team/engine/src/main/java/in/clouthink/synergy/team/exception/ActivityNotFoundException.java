package in.clouthink.synergy.team.exception;

/**
 *
 */
public class ActivityNotFoundException extends ActivityException {

	public ActivityNotFoundException() {
	}

	public ActivityNotFoundException(String message) {
		super(message);
	}

	public ActivityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActivityNotFoundException(Throwable cause) {
		super(cause);
	}
}
