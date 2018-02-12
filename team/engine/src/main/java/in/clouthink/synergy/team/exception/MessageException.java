package in.clouthink.synergy.team.exception;

/**
 *
 */
public class MessageException extends BizException {

	public MessageException() {
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}
}
