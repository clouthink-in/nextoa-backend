package in.clouthink.synergy.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "不存在的用户组")
public class GroupNotFoundException extends GroupException {

    public GroupNotFoundException() {
    }

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

}
