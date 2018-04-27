package in.clouthink.synergy.rbac.model;

import java.util.Set;

/**
 * @auther dz
 */
public interface PermissionSet extends PermissionChecker {

    /**
     * @return permissions
     */
    Set<Permission> getPermissions();

}
