package in.clouthink.synergy.account.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "不存在的角色")
public class RoleNotFoundException extends RoleException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }
}
