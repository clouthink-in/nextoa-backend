package in.clouthink.synergy.rbac.model;

/**
 * @auther dz
 */
public interface PermissionChecker {

    /**
     * @param api
     * @param action
     * @return
     */
    boolean isAllowed(String api, Action action);

}
