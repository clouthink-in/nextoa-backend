package in.clouthink.synergy.account.service.impl;

import in.clouthink.synergy.account.domain.model.*;
import in.clouthink.synergy.account.domain.request.SaveGroupRequest;
import in.clouthink.synergy.account.domain.request.SaveUserRequest;
import in.clouthink.synergy.account.domain.request.UsernameSearchRequest;
import in.clouthink.synergy.account.exception.*;
import in.clouthink.synergy.account.repository.GroupRepository;
import in.clouthink.synergy.account.repository.UserGroupRelationshipRepository;
import in.clouthink.synergy.account.repository.UserRepository;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.GroupService;
import in.clouthink.synergy.account.service.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

    private static final Log logger = LogFactory.getLog(GroupServiceImpl.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRelationshipRepository relationshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<Group> listRootGroups() {
        return groupRepository.findByParentOrderByCodeAscNameAsc(null);
    }

    @Override
    public List<Group> listGroupChildren(String groupId) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }
        return groupRepository.findByParentOrderByCodeAscNameAsc(group);
    }

    @Override
    public Page<User> listBindUsers(String groupId, UsernameSearchRequest queryParameter) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }

        return userRepository.queryPage(group, queryParameter);
    }

    @Override
    public Group findGroupById(String groupId) {
        return groupRepository.findById(groupId);
    }

    @Override
    public Group createGroup(SaveGroupRequest re, User byWho) {
        if (StringUtils.isEmpty(re.getName())) {
            throw new GroupException("用户组名称不能为空");
        }

        Group group = groupRepository.findByParentAndName(null, re.getName());
        if (group != null) {
            throw new GroupException("用户组名称不能重复");
        }

        group = new Group();
        group.setCode(re.getCode());
        group.setName(re.getName());
        group.setLeaf(true);
        group.setCreatedAt(new Date());
        group.setCreatedBy(byWho);
        return groupRepository.save(group);
    }

    @Override
    public void updateGroup(String groupId, SaveGroupRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new GroupException("用户组名称不能为空");
        }
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }

        Group groupByName = groupRepository.findByParentAndName(group.getParent(),
                                                                request.getName());
        if (groupByName != null && !groupByName.getId().equals(groupId)) {
            throw new GroupException("用户组名称不能重复");
        }

        group.setCode(request.getCode());
        group.setName(request.getName());
        group.setModifiedAt(new Date());
        group.setModifiedBy(byWho);
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(String groupId, User byWho) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }
        long userCountUnderGroup = relationshipRepository.countByGroup(group);

        if (userCountUnderGroup > 0) {
            throw new GroupException("该用户组下用户不为空,不能删除.");
        }
        long childCountUnderGroup = groupRepository.countByParent(group);
        if (childCountUnderGroup > 0) {
            throw new GroupException("该用户组下子用户组不为空,不能删除.");
        }

        Group parent = group.getParent();
        groupRepository.delete(group);
        if (parent != null && groupRepository.countByParent(parent) == 0) {
            parent.setLeaf(true);
            groupRepository.save(parent);
        }
    }

    @Override
    public Group createGroupChild(String groupId, SaveGroupRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new GroupException("用户组名称不能为空");
        }

        Group parent = groupRepository.findById(groupId);
        if (parent == null) {
            throw new GroupNotFoundException(groupId);
        }

        Group groupByName = groupRepository.findByParentAndName(parent, request.getName());
        if (groupByName != null) {
            throw new GroupException("用户组名称不能重复");
        }

        Group group = new Group();
        group.setCode(request.getCode());
        group.setName(request.getName());
        group.setCreatedAt(new Date());
        group.setCreatedBy(byWho);
        group.setLeaf(true);
        group.setParent(parent);
        Group target = groupRepository.save(group);
        parent.setLeaf(false);
        groupRepository.save(parent);
        return target;
    }

    @Override
    public User createUser(String groupId, SaveUserRequest request, User byWho) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }

        User user = accountService.createAccount(request, roleService.requireSysUserRole());
        UserGroupRelationship relationship = relationshipRepository.findByUserAndGroup(user, group);
        if (relationship == null) {
            relationship = new UserGroupRelationship();
            relationship.setUser(user);
            relationship.setGroup(group);
            relationship.setCreatedAt(new Date());
            relationshipRepository.save(relationship);
        }
        return user;
    }

    @Override
    public List<Group> listBindGroups(User user) {
        return relationshipRepository.findListByUser(user)
                                     .stream()
                                     .map(r -> r.getGroup())
                                     .collect(Collectors.toList());
    }

    @Override
    public void bindUserAndGroups(String userId, String[] groupIds) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Stream.of(groupIds).forEach(groupId -> {
            Group group = groupRepository.findById(groupId);
            if (group == null) {
                throw new GroupNotFoundException(groupId);
            }

            tryRelationship(user, group);
        });
    }

    @Override
    public void unbindUserAndGroups(String userId, String[] groupIds) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Stream.of(groupIds).forEach(groupId -> {
            Group group = groupRepository.findById(groupId);
            if (group == null) {
                return;
            }

            UserGroupRelationship relationship = relationshipRepository.findByUserAndGroup(user, group);
            if (relationship != null) {
                relationshipRepository.delete(relationship);
            }
        });
    }

    @Override
    public void bindGroupAndUsers(String groupId, String[] userIds) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }

        Stream.of(userIds).forEach(userId -> {
            User user = userRepository.findById(userId);
            if (user == null) {
                throw new UserNotFoundException(userId);
            }

            tryRelationship(user, group);
        });
    }

    @Override
    public void unbindGroupAndUsers(String groupId, String[] userIds) {
        Group group = groupRepository.findById(groupId);
        if (group == null) {
            throw new GroupNotFoundException(groupId);
        }

        Stream.of(userIds).forEach(userId -> {
            User user = userRepository.findById(userId);
            if (user == null) {
                return;
            }

            UserGroupRelationship relationship = relationshipRepository.findByUserAndGroup(user, group);
            if (relationship != null) {
                relationshipRepository.delete(relationship);
            }
        });
    }

    //*************************************************
    // private
    //*************************************************

    private void tryRelationship(User user, Group group) {
        UserGroupRelationship relationship = relationshipRepository.findByUserAndGroup(user, group);
        if (relationship == null) {
            relationship = new UserGroupRelationship();
            relationship.setGroup(group);
            relationship.setUser(user);
            relationship.setCreatedAt(new Date());

            relationshipRepository.save(relationship);
        }
    }
}
