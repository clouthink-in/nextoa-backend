package in.clouthink.synergy.account.domain.model;

/**
 * @auther dz
 */
public final class Users {

    /**
     * The administrator user's username (default value)
     */
    public static final String ADMIN_USER_NAME = "administrator";

    /**
     * @param user
     * @return true if the user is administrator
     */
    public static boolean isAdministrator(User user) {
        return ADMIN_USER_NAME.equals(user.getUsername());
    }

}
