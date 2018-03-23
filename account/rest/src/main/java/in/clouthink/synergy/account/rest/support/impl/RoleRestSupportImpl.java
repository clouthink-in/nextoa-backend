package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveRoleParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.support.RoleRestSupport;
import in.clouthink.synergy.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleRestSupportImpl implements RoleRestSupport {

    @Autowired
    private RoleService roleService;

    @Override
    public Page<RoleView> getRoles(RoleSearchRequest request, User user) {
        Page<Role> appRoles = roleService.listRoles(request);
        return new PageImpl<>(appRoles.getContent().stream().map(RoleView::from).collect(Collectors.toList()),
                              new PageRequest(request.getStart(), request.getLimit()),
                              appRoles.getTotalElements());
    }

    @Override
    public Page<UserView> getBindUsers(String roleId, UserSearchParam request, User user) {
        Page<User> users = roleService.listBindUsers(roleId, request);
        return new PageImpl<>(users.getContent().stream().map(UserView::from).collect(Collectors.toList()),
                              new PageRequest(request.getStart(), request.getLimit()),
                              users.getTotalElements());
    }

    @Override
    public Role createRole(SaveRoleParam request, User user) {
        return roleService.createRole(request, RoleType.APP_ROLE, user);
    }

    @Override
    public void updateRole(String roleId, SaveRoleParam request, User user) {
        roleService.updateRole(roleId, request, user);
    }

    @Override
    public void deleteRole(String roleId, User user) {
        roleService.deleteRole(roleId, user);
    }

    @Override
    public void bindRoleAndUsers(String roleId, String[] userIds, User user) {
        roleService.bindRoleAndUsers(roleId, userIds);
    }

    @Override
    public void unbindRoleAndUsers(String roleId, String[] userIds, User user) {
        roleService.unbindRoleAndUsers(roleId, userIds);
    }
}
