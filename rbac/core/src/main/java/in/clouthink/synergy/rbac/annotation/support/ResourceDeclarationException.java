package in.clouthink.synergy.rbac.annotation.support;

import in.clouthink.synergy.rbac.exception.ResourceException;

/**
 * resource exception
 *
 * @author dz
 */
public class ResourceDeclarationException extends ResourceException {

    public ResourceDeclarationException() {
    }

    public ResourceDeclarationException(String message) {
        super(message);
    }

    public ResourceDeclarationException(String message,
                                        Throwable cause) {
        super(message, cause);
    }

    public ResourceDeclarationException(Throwable cause) {
        super(cause);
    }

    public ResourceDeclarationException(String message,
                                        Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
