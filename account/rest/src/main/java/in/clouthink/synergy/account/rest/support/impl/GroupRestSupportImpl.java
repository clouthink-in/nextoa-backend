package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveGroupParam;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UsernameSearchParam;
import in.clouthink.synergy.account.rest.support.GroupRestSupport;
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
    public List<GroupView> listRootGroups() {
        return groupService.listRootGroups()
                           .stream()
                           .map(GroupView::from)
                           .collect(Collectors.toList());
    }

    @Override
    public List<GroupView> listGroupChildren(String id) {
        return groupService.listGroupChildren(id)
                           .stream()
                           .map(GroupView::from)
                           .collect(Collectors.toList());
    }

    @Override
    public Page<UserView> listBindUsers(String id, UsernameSearchParam queryRequest) {
        Page<User> userPage = groupService.listBindUsers(id, queryRequest);
        return new PageImpl<>(userPage.getContent().stream().map(UserView::from).collect(Collectors.toList()),
                              new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
                              userPage.getTotalElements());
    }

    @Override
    public String createGroup(SaveGroupParam request, User user) {
        return groupService.createGroup(request, user).getId();
    }

    @Override
    public void updateGroup(String groupId, SaveGroupParam request, User user) {
        groupService.updateGroup(groupId, request, user);
    }

    @Override
    public void deleteGroup(String groupId, User user) {
        groupService.deleteGroup(groupId, user);
    }

    @Override
    public String createGroupChild(String groupId, SaveGroupParam request, User user) {
        return groupService.createGroupChild(groupId, request, user).getId();
    }

    @Override
    public String createUserUnderGroup(String groupId, SaveUserParam request, User user) {
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
