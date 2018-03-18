package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleQueryRequest;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.RoleRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * RoleRestSupport mocker
 *
 * @author dz
 */
@Component
public class RoleRestSupportMocker implements RoleRestSupport {

    @Override
    public Page<RoleSummary> getRoles(RoleQueryRequest request, User user) {
        return null;
    }

    @Override
    public Page<UserSummary> getBindUsers(String roleId, UserQueryParameter request, User user) {
        return null;
    }

    @Override
    public Role createRole(SaveRoleParameter request, User user) {
        return null;
    }

    @Override
    public void updateRole(String roleId, SaveRoleParameter request, User user) {

    }

    @Override
    public void deleteRole(String roleId, User user) {

    }

    @Override
    public void bindRoleAndUsers(String roleId, String[] userIds, User user) {

    }

    @Override
    public void unbindRoleAndUsers(String roleId, String[] userIds, User user) {

    }
}
