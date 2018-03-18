package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleQueryRequest;
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
     * @param roleQueryRequest
     * @return
     */
    Page<Role> listRoles(RoleQueryRequest roleQueryRequest);

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
    Role createRole(SaveRoleRequest request, RoleType roleType, User byWho);

    /**
     * @param id
     * @param request
     */
    void updateRole(String id, SaveRoleRequest request, User byWho);

    /**
     * @param id
     */
    void deleteRole(String id, User byWho);

    /**
     * @param roleId
     * @param request
     * @return
     */
    Page<User> listBindUsers(String roleId, UserQueryRequest request);

    /**
     * @return
     */
    List<Role> listBindRoles(User user);

    /**
     * @param roleId
     * @param userIds
     */
    void bindRoleAndUsers(String roleId, String[] userIds);

    /**
     * @param roleId
     * @param userIds
     */
    void unbindRoleAndUsers(String roleId, String[] userIds);

    /**
     * @param userId
     * @param roleIds
     */
    void bindUserAndRoles(String userId, String[] roleIds);

    /**
     * @param userId
     * @param roleIds
     */
    void unbindUserAndRoles(String userId, String[] roleIds);

}
