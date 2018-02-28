package in.clouthink.synergy.team.exception;

/**
 * Unsupported request to engine
 *
 * @auther dz
 */
public class UnsupportedRequestException extends EngineException {

    public UnsupportedRequestException() {
    }

    public UnsupportedRequestException(String message) {
        super(message);
    }

    public UnsupportedRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedRequestException(Throwable cause) {
        super(cause);
    }

}
