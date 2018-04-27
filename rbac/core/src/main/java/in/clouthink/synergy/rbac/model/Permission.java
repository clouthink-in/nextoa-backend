package in.clouthink.synergy.rbac.model;

/**
 * The permission def
 */
public interface Permission extends PermissionChecker {

    static Permission create(String api, Action[] actions) {
        return new DefaultPermission(api, actions);
    }

    /**
     * @return The api url (ant pattern is supported)
     */
    String getApi();

    /**
     * @return If the action is null or empty , it means no action is allowed
     */
    Action[] getActions();

}
