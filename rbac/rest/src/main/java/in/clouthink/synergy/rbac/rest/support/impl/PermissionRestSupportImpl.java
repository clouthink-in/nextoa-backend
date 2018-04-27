package in.clouthink.synergy.rbac.rest.support.impl;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.impl.service.support.ResourceRoleRelationshipService;
import in.clouthink.synergy.rbac.rest.service.ResourceCacheService;
import in.clouthink.synergy.rbac.rest.support.PermissionRestSupport;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceView;
import in.clouthink.synergy.rbac.rest.view.ResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionRestSupportImpl implements PermissionRestSupport {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceCacheService resourceCacheService;

    @Autowired
    private ResourceRoleRelationshipService resourceRoleRelationshipService;

    @Override
    public List<PrivilegedResourceTreeView> listGrantedHierarchyResources(String roleCode) {
        //granted resource codes & action codes
        Set<String> resourceCodes = resourceRoleRelationshipService.listGrantedResources(roleCode)
                                                                   .stream()
                                                                   .map(resource -> resource.getResourceCode())
                                                                   .collect(Collectors.toSet());

        List<ResourceTreeView> resourceTree = resourceCacheService.listHierarchyResources(false);

        List<PrivilegedResourceTreeView> result = resourceTree.stream()
                                                              .map(PrivilegedResourceTreeView::from)
                                                              .collect(Collectors.toList());

        processChildren(result, resourceCodes);

        return result;
    }

    @Override
    public List<PrivilegedResourceView> listGrantedFlattenResources(String roleCode) {
        //granted resource codes & action codes
        Set<String> resourceCodes = resourceRoleRelationshipService.listGrantedResources(roleCode)
                                                                   .stream()
                                                                   .map(resource -> resource.getResourceCode())
                                                                   .collect(Collectors.toSet());

        List<ResourceView> result = resourceCacheService.listFlattenResources(false);

        return result.stream().map(
                item -> {
                    PrivilegedResourceView pri = PrivilegedResourceView.from(item);
                    pri.setGranted(resourceCodes.contains(item.getCode()));
                    return pri;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<TypedRole> listGrantedRoles(String code) {
        return resourceRoleRelationshipService.listGrantedRoles(code)
                                              .stream()
                                              .map(TypedRole::from)
                                              .collect(Collectors.toList());
    }

    @Override
    public void grantResourcesToRole(String roleCode, String resourceCode) {
        Role role = roleService.findByCode(roleCode);
        if (role != null) {
            resourceRoleRelationshipService.grant(resourceCode, role);
        }
    }

    @Override
    public void revokeResourcesFromRole(String roleCode, String resourceCode) {
        Role role = roleService.findByCode(roleCode);
        if (role != null) {
            resourceRoleRelationshipService.revoke(resourceCode, role);
        }
    }

    //***************************************************************************
    // private
    //***************************************************************************
    private void processChildren(List<PrivilegedResourceTreeView> result,
                                 Set<String> resourceCodes) {
        result.stream().forEach(resourceWithChildren -> {
            resourceWithChildren.setGranted(resourceCodes.contains(resourceWithChildren.getCode()));
            processChildren(resourceWithChildren.getChildren(), resourceCodes);
        });
    }

}
