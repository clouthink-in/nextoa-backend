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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class GroupRestSupportImpl implements GroupRestSupport {

	@Autowired
	private GroupService groupService;

	@Autowired
	private AccountService accountService;

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
	public Page<UserSummary> listUsersOfGroup(String id, UsernamePageQueryParameter queryRequest) {
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
	public void updateGroup(String id, SaveGroupParameter request, User user) {
		groupService.updateGroup(id, request, user);
	}

	@Override
	public void deleteGroup(String id, User user) {
		groupService.deleteGroup(id, user);
	}

	@Override
	public String createGroupChild(String id, SaveGroupParameter request, User user) {
		return groupService.createGroupChild(id, request, user).getId();
	}

	@Override
	public String createAppUser(String id, SaveUserParameter request, User user) {
		return groupService.createUser(id, request, user).getId();
	}

	@Override
	public void updateAppUserGroupRelationship(String userId, String[] groupIds) {
		groupService.bindUserAndGroups(userId, groupIds);
	}

	@Override
	public List<GroupOfAppUser> getAppUserGroupRelationship(String userId) {
		User user = accountService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		List<Group> groups = user.getGroups();
		if (groups == null) {
			return null;
		}
		return groups.stream().map(GroupOfAppUser::from).collect(Collectors.toList());
	}

}
