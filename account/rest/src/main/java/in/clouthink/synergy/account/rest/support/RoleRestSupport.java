package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleQueryRequest;
import in.clouthink.synergy.account.rest.dto.*;
import org.springframework.data.domain.Page;

public interface RoleRestSupport {

    Page<RoleSummary> getRoles(RoleQueryRequest request, User user);

    Role createRole(SaveRoleParameter request, User user);

    void updateRole(String roleId, SaveRoleParameter request, User user);

    void deleteRole(String roleId, User user);

    Page<UserSummary> getBindUsers(String roleId, UserQueryParameter request, User user);

    void bindRoleAndUsers(String roleId, String[] userIds, User user);

    void unbindRoleAndUsers(String roleId, String[] userIds, User user);

}
