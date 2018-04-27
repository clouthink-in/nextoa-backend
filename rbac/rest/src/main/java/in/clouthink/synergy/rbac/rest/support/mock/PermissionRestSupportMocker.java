package in.clouthink.synergy.rbac.rest.support.mock;

import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.rest.support.PermissionRestSupport;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRestSupportMocker implements PermissionRestSupport {

    @Override
    public List<PrivilegedResourceTreeView> listGrantedHierarchyResources(String roleCode) {
        return null;
    }

    @Override
    public List<PrivilegedResourceView> listGrantedFlattenResources(String roleCode) {
        return null;
    }

    @Override
    public List<TypedRole> listGrantedRoles(String resourceCode) {
        return null;
    }

    @Override
    public void grantResourcesToRole(String roleCode, String resourceCode) {

    }

    @Override
    public void revokeResourcesFromRole(String roleCode, String resourceCode) {

    }
}
