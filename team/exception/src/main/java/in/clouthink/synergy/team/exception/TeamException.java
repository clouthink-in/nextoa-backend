package in.clouthink.synergy.team.exception;

/**
 *
 */
public class TeamException extends RuntimeException {
	public TeamException() {
	}

	public TeamException(String message) {
		super(message);
	}

	public TeamException(String message, Throwable cause) {
		super(message, cause);
	}

	public TeamException(Throwable cause) {
		super(cause);
	}
}
