package in.clouthink.synergy.rbac.service;

import in.clouthink.synergy.rbac.model.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * The Role-Based (GrantedAuthority-Based) Access Control service
 */
public interface PermissionService {

    /**
     * @param resourceCode
     * @return
     */
    List<Role> getGrantedRoles(String resourceCode);

    /**
     * @param resourceCode
     * @param role
     * @return the privilege
     */
    Privilege getPrivilege(String resourceCode, GrantedAuthority role);

    /**
     * @param resourceCode
     * @param roles
     * @return the privilege
     */
    Privilege getPrivilege(String resourceCode, List<GrantedAuthority> roles);

    /**
     * @param role
     * @return
     */
    List<Resource> getGrantedResources(GrantedAuthority role);

    /**
     * @param roles
     * @return
     */
    List<Resource> getGrantedResources(List<GrantedAuthority> roles);

    /**
     * @param role
     * @param filter
     * @return
     */
    List<Resource> getGrantedResources(GrantedAuthority role, ResourceMatcher filter);

    /**
     * @param roles
     * @param filter
     * @return
     */
    List<Resource> getGrantedResources(List<GrantedAuthority> roles, ResourceMatcher filter);

    /**
     * @param resourceCode
     * @param role
     * @return
     */
    boolean isGranted(String resourceCode, GrantedAuthority role);

}
