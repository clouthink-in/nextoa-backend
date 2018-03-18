package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.exception.UserNotFoundException;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.GroupRestSupport;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupRestSupportImpl implements GroupRestSupport {

    @Autowired
    private GroupService groupService;

    @Override
    public List<GroupSummary> listRootGroups() {
        return groupService.listRootGroups()
                           .stream()
                           .map(GroupSummary::from)
                           .collect(Collectors.toList());
    }

    @Override
    public List<GroupSummary> listGroupChildren(String id) {
        return groupService.listGroupChildren(id)
                           .stream()
                           .map(GroupSummary::from)
                           .collect(Collectors.toList());
    }

    @Override
    public Page<UserSummary> listBindUsers(String id, UsernamePageQueryParameter queryRequest) {
        Page<User> userPage = groupService.listBindUsers(id, queryRequest);
        return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              userPage.getTotalElements());
    }

    @Override
    public String createGroup(SaveGroupParameter request, User user) {
        return groupService.createGroup(request, user).getId();
    }

    @Override
    public void updateGroup(String groupId, SaveGroupParameter request, User user) {
        groupService.updateGroup(groupId, request, user);
    }

    @Override
    public void deleteGroup(String groupId, User user) {
        groupService.deleteGroup(groupId, user);
    }

    @Override
    public String createGroupChild(String groupId, SaveGroupParameter request, User user) {
        return groupService.createGroupChild(groupId, request, user).getId();
    }

    @Override
    public String createUserUnderGroup(String groupId, SaveUserParameter request, User user) {
        return groupService.createUser(groupId, request, user).getId();
    }

    @Override
    public void bindGroupAndUsers(String groupId, String[] userIds, User user) {
        groupService.bindGroupAndUsers(groupId, userIds);
    }

    @Override
    public void unbindGroupAndUsers(String groupId, String[] userIds, User user) {
        groupService.unbindGroupAndUsers(groupId, userIds);
    }
}
