package in.clouthink.synergy.rbac.rest.support;

import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceView;

import java.util.List;

/**
 * @author dz
 */
public interface PermissionRestSupport {

    /**
     * @param roleCode
     * @return
     */
    List<PrivilegedResourceTreeView> listGrantedHierarchyResources(String roleCode);

    /**
     * @param roleCode
     * @return
     */
    List<PrivilegedResourceView> listGrantedFlattenResources(String roleCode);

    /**
     * @param resourceCode
     * @return
     */
    List<TypedRole> listGrantedRoles(String resourceCode);

    /**
     * @param roleCode
     * @param resourceCode
     */
    void grantResourcesToRole(String roleCode, String resourceCode);

    /**
     * @param roleCode
     * @param resourceCode
     */
    void revokeResourcesFromRole(String roleCode, String resourceCode);

}
