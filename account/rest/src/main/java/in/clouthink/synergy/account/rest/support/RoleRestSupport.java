package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveRoleParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import org.springframework.data.domain.Page;

public interface RoleRestSupport {

    Page<RoleView> getRoles(RoleSearchRequest request, User user);

    Role createRole(SaveRoleParam request, User user);

    void updateRole(String roleId, SaveRoleParam request, User user);

    void deleteRole(String roleId, User user);

    Page<UserView> getBindUsers(String roleId, UserSearchParam request, User user);

    void bindRoleAndUsers(String roleId, String[] userIds, User user);

    void unbindRoleAndUsers(String roleId, String[] userIds, User user);

}
