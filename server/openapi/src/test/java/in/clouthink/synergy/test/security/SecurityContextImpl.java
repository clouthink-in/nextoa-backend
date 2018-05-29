package in.clouthink.synergy.test.security;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContext;

/**
 * The SecurityContext Spring Security Implementation for Test ENV.
 */
public class SecurityContextImpl implements SecurityContext {


    @Override
    public User currentUser() {
        //TODO
        return null;
    }

    @Override
    public User requireUser() {
        //TODO
        return null;
    }

}
