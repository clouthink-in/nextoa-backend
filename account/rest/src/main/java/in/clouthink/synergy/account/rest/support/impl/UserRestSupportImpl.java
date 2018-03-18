package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.exception.UserNotFoundException;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.UserRestSupport;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.GroupService;
import in.clouthink.synergy.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRestSupportImpl implements UserRestSupport {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;

    @Override
    public Page<UserSummary> listUsers(UserQueryParameter queryRequest) {
        Page<User> userPage = accountService.listUsers(queryRequest);
        return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              userPage.getTotalElements());
    }

    @Override
    public UserDetail getUserDetail(String id) {
        User user = accountService.findById(id);
        return UserDetail.from(user);
    }

    @Override
    public User createUser(SaveUserParameter request, User byWho) {
        return accountService.createAccount(request, roleService.requireSysUserRole());
    }

    @Override
    public void updateUser(String id, SaveUserParameter request, User byWho) {
        accountService.updateAccount(id, request);
    }

    @Override
    public void deleteUser(String id, User byWho) {
        accountService.archiveAccount(id, byWho);
    }

    @Override
    public void changePassword(String id, ChangePasswordRequest request, User byWho) {
        accountService.changePassword(id, request.getNewPassword());
    }

    @Override
    public void enableUser(String id, User byWho) {
        accountService.enable(id);
    }

    @Override
    public void disableUser(String id, User byWho) {
        accountService.disable(id);
    }

    @Override
    public void lockUser(String id, User byWho) {
        accountService.lock(id);
    }

    @Override
    public void unlockUser(String id, User byWho) {
        accountService.unlock(id);
    }

    @Override
    public List<GroupWithPath> listBindGroups(String userId) {
        User user = accountService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        List<Group> groups = groupService.listBindGroups(user);
        if (groups == null) {
            return Collections.EMPTY_LIST;
        }
        return groups.stream().map(GroupWithPath::from).collect(Collectors.toList());
    }

    @Override
    public void bindUserAndGroups(String userId, String[] groupIds, User byWho) {
        groupService.bindUserAndGroups(userId, groupIds);
    }

    @Override
    public void unbindUserAndGroups(String userId, String[] groupIds, User byWho) {
        groupService.unbindUserAndGroups(userId, groupIds);
    }

    @Override
    public List<RoleSummary> listBindRoles(String userId) {
        User user = accountService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        List<Role> roles = roleService.listBindRoles(user);
        if (roles == null) {
            return Collections.EMPTY_LIST;
        }
        return roles.stream().map(RoleSummary::from).collect(Collectors.toList());
    }

    @Override
    public void bindUserAndRoles(String userId, String[] roleIds, User byWho) {
        roleService.unbindUserAndRoles(userId, roleIds);
    }

    @Override
    public void unbindUserAndRoles(String userId, String[] roleIds, User byWho) {
        roleService.unbindUserAndRoles(userId, roleIds);
    }

}
