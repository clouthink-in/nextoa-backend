package in.clouthink.synergy.rbac.impl.service.impl;

import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.impl.repository.ResourceRoleRelationshipRepository;
import in.clouthink.synergy.rbac.model.*;
import in.clouthink.synergy.rbac.service.PermissionService;
import in.clouthink.synergy.rbac.service.ResourceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * the default permission service impl.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ResourceDiscovery resourceDiscovery;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceRoleRelationshipRepository resourceRoleRelationshipRepository;

    @Override
    public Privilege getPrivilege(String resourceCode, GrantedAuthority authority) {
        return getPrivilege(resourceCode, Arrays.asList(authority));
    }

    @Override
    public Privilege getPrivilege(String resourceCode, List<GrantedAuthority> roles) {
        for (GrantedAuthority r : roles) {
            String roleCode = Roles.resolveRoleCode(r);
            ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                    resourceCode, roleCode);
            if (resourceRoleRelationship != null) {
                Resource resource = resourceDiscovery.findByCode(resourceCode);
                in.clouthink.synergy.account.domain.model.Role role = roleService.findByCode(roleCode);
                return new DefaultPrivilege(new DefaultRole(role.getCode(), role.getName()), resource);
            }
        }

        return null;
    }

    @Override
    public List<Role> getGrantedRoles(String resourceCode) {
        return resourceRoleRelationshipRepository.findListByResourceCode(resourceCode)
                                                 .stream()
                                                 .map(relationship -> relationship.getRoleCode())
                                                 .collect(Collectors.toSet())
                                                 .stream()
                                                 .map(roleCode -> {
                                                     in.clouthink.synergy.account.domain.model.Role role = roleService.findByCode(
                                                             roleCode);
                                                     TypedRole result = new TypedRole();
                                                     result.setCode(role.getCode());
                                                     result.setName(role.getName());
                                                     result.setType(role.getType());
                                                     return result;
                                                 })
                                                 .collect(Collectors.toList());
    }

    @Override
    public List<Resource> getGrantedResources(GrantedAuthority role) {
        return Optional.ofNullable(role)
                       .map(r -> getGrantedResources(Arrays.asList(r)))
                       .orElse(Collections.emptyList());
    }

    @Override
    public List<Resource> getGrantedResources(List<GrantedAuthority> roles) {
        return roles.stream()
                    .filter(role -> Roles.ROLE_ADMIN.getAuthority().equals(role.getAuthority()))
                    .findFirst()
                    .map(role -> resourceDiscovery.getFlattenResources())
                    .orElseGet(() -> doGetGrantedResources(resourceDiscovery.getFlattenResources(), roles));
    }

    private List<Resource> doGetGrantedResources(List<? extends Resource> existedResources,
                                                 List<GrantedAuthority> roles) {
        List<Resource> result = new ArrayList<>();

        //and return the granted resource
        existedResources.stream().forEach(resource -> {
            for (GrantedAuthority role : roles) {
                ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                        resource.getCode(),
                        Roles.resolveRoleCode(role));
                if (resourceRoleRelationship != null) {
                    result.add(resource);
                    break;
                }
            }
        });

        return result;
    }

    @Override
    public List<Resource> getGrantedResources(GrantedAuthority role, ResourceMatcher filter) {
        return getGrantedResources(Arrays.asList(role), filter);
    }

    @Override
    public List<Resource> getGrantedResources(List<GrantedAuthority> roles, ResourceMatcher filter) {
        return getGrantedResources(roles).stream()
                                         .filter(resource -> filter.matched(resource))
                                         .collect(Collectors.toList());
    }

    @Override
    public boolean isGranted(String resourceCode, GrantedAuthority role) {
        if (Roles.ROLE_ADMIN.getAuthority().equalsIgnoreCase(role.getAuthority())) {
            return true;
        }

        return null !=
                resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(resourceCode,
                                                                                 Roles.resolveRoleCode(role));
    }

}
