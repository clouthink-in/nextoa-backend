package in.clouthink.synergy.rbac.impl.service.support.impl;

import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.synergy.rbac.impl.repository.ResourceRoleRelationshipRepository;
import in.clouthink.synergy.rbac.impl.service.support.ResourceRoleRelationshipService;
import in.clouthink.synergy.rbac.model.Resource;
import in.clouthink.synergy.rbac.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ResourceRoleRelationshipService impl
 *
 * @author dz
 */
@Service
public class ResourceRoleRelationshipServiceImpl implements ResourceRoleRelationshipService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRoleRelationshipRepository resourceRoleRelationshipRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public List<ResourceRoleRelationship> listGrantedResources(GrantedAuthority role) {
        return listGrantedResources(Roles.resolveRoleCode(role));
    }

    @Override
    public List<ResourceRoleRelationship> listGrantedResources(String roleCode) {
        return resourceRoleRelationshipRepository.findListByRoleCode(roleCode);
    }

    @Override
    public List<String> listGrantedRoleCodes(String resourceCode) {
        return resourceRoleRelationshipRepository.findListByResourceCode(resourceCode)
                                                 .stream()
                                                 .map(relationship -> relationship.getRoleCode())
                                                 .collect(Collectors.toList());
    }

    @Override
    public List<String> listGrantedRoleCodes(Resource resource) {
        return listGrantedRoleCodes(resource.getCode());
    }

    @Override
    public List<GrantedAuthority> listGrantedRoles(String resourceCode) {
        return resourceRoleRelationshipRepository.findListByResourceCode(resourceCode).stream().map(relationship ->
                                                                                                            roleService.findByCode(
                                                                                                                    relationship
                                                                                                                            .getRoleCode())
        ).collect(Collectors.toList());
    }

    @Override
    public List<GrantedAuthority> listGrantedRoles(Resource resource) {
        return listGrantedRoles(resource.getCode());
    }

    @Override
    public void grantPermission(String resourceCode, String[] actionCodes, GrantedAuthority role) {
        String roleCode = Roles.resolveRoleCode(role);
        Resource resource = resourceService.findByCode(resourceCode);
        if (resource == null) {
            return;
        }
        ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                resourceCode,
                roleCode);
        if (resourceRoleRelationship == null) {
            resourceRoleRelationship = new ResourceRoleRelationship();
            resourceRoleRelationship.setResourceCode(resourceCode);
            resourceRoleRelationship.setRoleCode(roleCode);
        }
        //filter the invalid action
        final Set<String> resourceActionCodes = resource.getActions()
                                                        .stream()
                                                        .map(action -> action.getCode())
                                                        .collect(Collectors.toSet());

        resourceRoleRelationship.setAllowedActions(Arrays.asList(actionCodes)
                                                         .stream()
                                                         .filter(code -> resourceActionCodes.contains(code))
                                                         .collect(Collectors.toList()));
        resourceRoleRelationshipRepository.save(resourceRoleRelationship);

        Resource parentResource = resourceService.getResourceParent(resource.getCode());
        if (parentResource == null) {
            return;
        }

        //The parent will be granted automatically if the child is granted
        ResourceRoleRelationship parentResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                parentResource.getCode(),
                roleCode);
        if (parentResourceRoleRelationship == null) {
            parentResourceRoleRelationship = new ResourceRoleRelationship();
            parentResourceRoleRelationship.setResourceCode(parentResource.getCode());
            parentResourceRoleRelationship.setRoleCode(roleCode);
            resourceRoleRelationshipRepository.save(parentResourceRoleRelationship);
        }
    }

    @Override
    public void revokePermission(String resourceCode, GrantedAuthority role) {
        String roleCode = Roles.resolveRoleCode(role);

        ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                resourceCode,
                roleCode);
        if (resourceRoleRelationship != null) {
            resourceRoleRelationshipRepository.delete(resourceRoleRelationship);
        }

        Resource parentResource = resourceService.getResourceParent(resourceCode);
        if (parentResource == null) {
            return;
        }

        //The parent will be revoked automatically if the child is revoked
        ResourceRoleRelationship parentResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                parentResource.getCode(),
                roleCode);
        if (parentResourceRoleRelationship != null) {
            //if no child is granted , the parent can be revoke
            List<Resource> children = resourceService.getResourceChildren(parentResource.getCode());

            boolean hasChildGranted = false;
            for (Resource child : children) {
                ResourceRoleRelationship childResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                        child.getCode(),
                        roleCode);
                if (childResourceRoleRelationship != null) {
                    hasChildGranted = true;
                    break;
                }
            }

            if (!hasChildGranted) {
                resourceRoleRelationshipRepository.delete(parentResourceRoleRelationship);
            }
        }
    }

}
