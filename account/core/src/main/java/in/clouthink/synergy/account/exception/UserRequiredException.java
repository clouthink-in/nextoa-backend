package in.clouthink.synergy.account.exception;

import in.clouthink.daas.security.token.exception.AuthenticationRequiredException;
import in.clouthink.daas.we.HttpStatusProvider;
import org.springframework.http.HttpStatus;

public class UserRequiredException extends AuthenticationRequiredException implements HttpStatusProvider {

    public UserRequiredException() {
    }

    public UserRequiredException(String message) {
        super(message);
    }

    public UserRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRequiredException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
