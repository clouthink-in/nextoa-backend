package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.AppRole;
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
     * @param request
     * @return
     */
    Page<AppRole> listAppRoles(RoleQueryRequest request);

    /**
     * @return
     */
    List<AppRole> listAppRoles();

    /**
     * @param id
     * @param request
     * @return
     */
    Page<User> listBindUsers(String id, UserQueryRequest request);

    /**
     * @param id
     * @return
     */
    AppRole findById(String id);

    /**
     * @param code
     * @return
     */
    AppRole findByCode(String code);

    /**
     * @param request
     * @return
     */
    AppRole createAppRole(SaveRoleRequest request);

    /**
     * @param id
     * @param request
     */
    void updateAppRole(String id, SaveRoleRequest request);

    /**
     * @param id
     */
    void deleteAppRole(String id);

    /**
     * @param id
     * @param userIds
     */
    void bindUsers4AppRole(String id, List<String> userIds);

    /**
     * @param id
     * @param userIds
     */
    void unBindUsers4AppRole(String id, List<String> userIds);

    /**
     * @param id
     * @param userIds
     */
    void bindUsers4SysRole(String id, List<String> userIds);

    /**
     * @param id
     * @param userIds
     */
    void unBindUsers4SysRole(String id, List<String> userIds);

}
