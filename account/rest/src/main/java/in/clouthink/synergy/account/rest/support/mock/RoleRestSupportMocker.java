package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveRoleParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
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
    public Page<RoleView> getRoles(RoleSearchRequest request, User user) {
        return null;
    }

    @Override
    public Page<UserView> getBindUsers(String roleId, UserSearchParam request, User user) {
        return null;
    }

    @Override
    public Role createRole(SaveRoleParam request, User user) {
        return null;
    }

    @Override
    public void updateRole(String roleId, SaveRoleParam request, User user) {

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
