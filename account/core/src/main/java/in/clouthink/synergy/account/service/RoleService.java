package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.SaveRoleRequest;
import in.clouthink.synergy.account.domain.request.UserQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The role service to manage the app & sys roles
 *
 * @author dz
 */
public interface RoleService {

    /**
     * @return the sys admin role
     * @throw exception if the role is not builtin
     */
    Role requireSysAdminRole();

    /**
     * @return the sys mgr role
     * @throw exception if the role is not builtin
     */
    Role requireSysMgrRole();

    /**
     * @return the sys user role
     * @throw exception if the role is not builtin
     */
    Role requireSysUserRole();

    /**
     * @return
     */
    List<Role> listRoles(RoleType roleType);

    /**
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * @param code
     * @return
     */
    Role findByCode(String code);

    /**
     * @param request
     * @return
     */
    Role createRole(SaveRoleRequest request, RoleType roleType);

    /**
     * @param id
     * @param request
     */
    void updateRole(String id, SaveRoleRequest request);

    /**
     * @param id
     */
    void deleteRole(String id);

    /**
     * @param id
     * @param request
     * @return
     */
    Page<User> listBindUsers(String id, UserQueryRequest request);

    /**
     * @return
     */
    List<Role> listBindRoles(User user);

    /**
     * @param roleId
     * @param userIds
     */
    void bindRoleAndUsers(String roleId, List<String> userIds);

    /**
     * @param roleId
     * @param userIds
     */
    void unbindRoleAndUsers(String roleId, List<String> userIds);

    /**
     * @param userId
     * @param roleIds
     */
    void bindUserAndRoles(String userId, List<String> roleIds);

    /**
     * @param userId
     * @param roleIds
     */
    void unbindUserAndRoles(String userId, List<String> roleIds);

}
