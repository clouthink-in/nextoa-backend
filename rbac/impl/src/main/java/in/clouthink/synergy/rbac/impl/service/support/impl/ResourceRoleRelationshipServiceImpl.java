package in.clouthink.synergy.rbac.impl.service.support.impl;

import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.synergy.rbac.impl.repository.ResourceRoleRelationshipRepository;
import in.clouthink.synergy.rbac.impl.service.support.ResourceRoleRelationshipService;
import in.clouthink.synergy.rbac.model.Resource;
import in.clouthink.synergy.rbac.service.ResourceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ResourceRoleRelationshipService impl
 *
 * @author dz
 */
@Service
public class ResourceRoleRelationshipServiceImpl implements ResourceRoleRelationshipService {

    @Autowired
    private ResourceDiscovery resourceDiscovery;

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
        return resourceRoleRelationshipRepository.findListByResourceCode(resourceCode)
                                                 .stream()
                                                 .map(relationship ->
                                                              roleService.findByCode(
                                                                      relationship
                                                                              .getRoleCode())
                                                 )
                                                 .collect(Collectors.toList());
    }

    @Override
    public List<GrantedAuthority> listGrantedRoles(Resource resource) {
        return listGrantedRoles(resource.getCode());
    }

    @Override
    public void grant(String resourceCode, GrantedAuthority role) {
        String roleCode = Roles.resolveRoleCode(role);
        Resource resource = resourceDiscovery.findByCode(resourceCode);
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
            resourceRoleRelationshipRepository.save(resourceRoleRelationship);
        }
    }

    @Override
    public void revoke(String resourceCode, GrantedAuthority role) {
        String roleCode = Roles.resolveRoleCode(role);

        ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
                resourceCode,
                roleCode);
        if (resourceRoleRelationship != null) {
            resourceRoleRelationshipRepository.delete(resourceRoleRelationship);
        }
    }

}
