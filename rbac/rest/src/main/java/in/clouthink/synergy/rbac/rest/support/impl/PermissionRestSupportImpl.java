package in.clouthink.synergy.rbac.rest.support.impl;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.impl.service.support.RbacUtils;
import in.clouthink.synergy.rbac.impl.service.support.ResourceRoleRelationshipService;
import in.clouthink.synergy.rbac.rest.param.GrantResourceParam;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;
import in.clouthink.synergy.rbac.rest.service.ResourceCacheService;
import in.clouthink.synergy.rbac.rest.support.PermissionRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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
    public List<PrivilegedResourceWithChildrenView> listGrantedResources(String roleCode) {
        //granted resource codes & action codes
        Map<String, Set<String>> resourceCodes =
                resourceRoleRelationshipService.listGrantedResources(roleCode)
                                               .stream()
                                               .collect(Collectors.toMap(resource -> resource.getResourceCode(),
                                                                         resource -> resource.getAllowedActions()
                                                                                             .stream()
                                                                                             .collect(Collectors.toSet())));

        List<PrivilegedResourceWithChildrenView> result = resourceCacheService.listResources(false);

        processChildren(result, resourceCodes);

        return result;
    }

    private void processChildren(List<PrivilegedResourceWithChildrenView> result, Map<String, Set<String>> resourceCodes) {
        result.stream().forEach(resourceWithChildren -> {
            resourceWithChildren.setGranted(resourceCodes.containsKey(resourceWithChildren.getCode()));
            resourceWithChildren.getActions().stream().forEach(action -> {
                Set<String> actionCodes = resourceCodes.get(resourceWithChildren.getCode());
                action.setGranted(actionCodes != null && actionCodes.contains(action.getCode()));
            });

            processChildren(resourceWithChildren.getChildren(), resourceCodes);
        });
    }

    @Override
    public List<TypedRole> listGrantedRoles(String code) {
        return resourceRoleRelationshipService.listGrantedRoles(code)
                                              .stream()
                                              .map(authority -> RbacUtils.convertToTypedRole(authority))
                                              .collect(Collectors.toList());
    }

    @Override
    public void grantResourcesToRole(String roleCode, GrantResourceParam parameter) {
        String resourceCode = parameter.getResourceCode();
        String[] actionCodes = parameter.getActionCodes();

        Role role = roleService.findByCode(roleCode);
        if (role != null) {
            resourceRoleRelationshipService.grantPermission(resourceCode, actionCodes, role);
        }
    }

    @Override
    public void revokeResourcesFromRole(String roleCode, String resourceCode) {
        Role role = roleService.findByCode(roleCode);
        if (role != null) {
            resourceRoleRelationshipService.revokePermission(resourceCode, role);
        }
    }

}
