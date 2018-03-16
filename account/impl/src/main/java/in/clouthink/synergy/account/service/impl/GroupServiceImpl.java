package in.clouthink.synergy.account.service.impl;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.SysRole;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.model.UserType;
import in.clouthink.synergy.account.domain.request.SaveGroupRequest;
import in.clouthink.synergy.account.domain.request.SaveUserRequest;
import in.clouthink.synergy.account.domain.request.UsernameQueryRequest;
import in.clouthink.synergy.account.exception.GroupException;
import in.clouthink.synergy.account.exception.GroupNotFoundException;
import in.clouthink.synergy.account.exception.UserException;
import in.clouthink.synergy.account.exception.UserNotFoundException;
import in.clouthink.synergy.account.repository.GroupRepository;
import in.clouthink.synergy.account.repository.UserRepository;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.GroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

    private static final Log logger = LogFactory.getLog(GroupServiceImpl.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public List<Group> listRootOrgainizations() {
        return groupRepository.findByParentOrderByCodeAscNameAsc(null);
    }

    @Override
    public List<Group> listOrgainizationChildren(String id) {
        Group organization = groupRepository.findById(id);
        if (organization == null) {
            throw new GroupNotFoundException(id);
        }
        return groupRepository.findByParentOrderByCodeAscNameAsc(organization);
    }

    @Override
    public Page<User> listUsersOfGroup(String id, UsernameQueryRequest queryParameter) {
        Group organization = groupRepository.findById(id);
        if (organization == null) {
            throw new GroupNotFoundException(id);
        }

        return userRepository.queryPage(organization, queryParameter);
    }

    @Override
    public Group findGroupById(String id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group createGroup(SaveGroupRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new GroupException("组织机构名称不能为空");
        }

        Group group = groupRepository.findByParentAndName(null, request.getName());
        if (group != null) {
            throw new GroupException("组织机构名称不能重复");
        }

        group = new Group();
        group.setCode(request.getCode());
        group.setName(request.getName());
        group.setLeaf(true);
        group.setCreatedAt(new Date());
        group.setCreatedBy(byWho);
        return groupRepository.save(group);
    }

    @Override
    public void updateGroup(String id, SaveGroupRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new GroupException("组织机构名称不能为空");
        }
        Group group = groupRepository.findById(id);
        if (group == null) {
            throw new GroupNotFoundException(id);
        }

        Group orgByName = groupRepository.findByParentAndName(group.getParent(),
                                                              request.getName());
        if (orgByName != null && !orgByName.getId().equals(id)) {
            throw new GroupException("组织机构名称不能重复");
        }

        group.setCode(request.getCode());
        group.setName(request.getName());
        group.setModifiedAt(new Date());
        group.setModifiedBy(byWho);
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(String id, User byWho) {
        Group organization = groupRepository.findById(id);
        if (organization == null) {
            throw new GroupNotFoundException(id);
        }
        long userCountUnderOrg = userRepository.countByGroup(organization);
        if (userCountUnderOrg > 0) {
            throw new GroupException("该组织机构下用户不为空,不能删除.");
        }
        long childCountUnderOrg = groupRepository.countByParent(organization);
        if (childCountUnderOrg > 0) {
            throw new GroupException("该组织机构下子部门不为空,不能删除.");
        }

        Group parent = organization.getParent();
        groupRepository.delete(organization);
        if (parent != null && groupRepository.countByParent(parent) == 0) {
            parent.setLeaf(true);
            groupRepository.save(parent);
        }
    }

    @Override
    public Group createGroupChild(String id, SaveGroupRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new GroupException("组织机构名称不能为空");
        }

        Group parent = groupRepository.findById(id);
        if (parent == null) {
            throw new GroupNotFoundException(id);
        }

        Group orgByName = groupRepository.findByParentAndName(parent, request.getName());
        if (orgByName != null) {
            throw new GroupException("组织机构名称不能重复");
        }

        Group organization = new Group();
        organization.setCode(request.getCode());
        organization.setName(request.getName());
        organization.setCreatedAt(new Date());
        organization.setCreatedBy(byWho);
        organization.setLeaf(true);
        organization.setParent(parent);
        Group target = groupRepository.save(organization);
        parent.setLeaf(false);
        groupRepository.save(parent);
        return target;
    }

    @Override
    public User createAppUser(String id, SaveUserRequest request, User byWho) {
        Group parent = groupRepository.findById(id);
        if (parent == null) {
            throw new GroupNotFoundException(id);
        }

//        return accountService.createAccount(UserType.APPUSER, request, parent, SysRole.ROLE_USER);
        return accountService.createAccount(request, parent, SysRole.ROLE_USER);
    }

    @Override
    public void updateAppUser(String id, SaveUserRequest request, User byWho) {
        accountService.updateAccount(id, request);
    }

    @Override
    public void deleteAppUser(String id, User byWho) {
        accountService.archiveAccount(id, byWho);
    }

    @Override
    public void updateAppUserGroupRelationship(String userId, String[] organizationIds) {
        User appUser = userRepository.findById(userId);
        if (appUser == null) {
            throw new UserNotFoundException(userId);
        }

//        if (UserType.SYSUSER == appUser.getUserType()) {
//            throw new UserException("系统管理用户不需要设置所属部门");
//        }

        if (organizationIds == null || organizationIds.length == 0) {
            throw new UserException("应用用户至少应该归属于一个部门");
        }

        List<Group> groups = new ArrayList<>();
        for (String orgId : organizationIds) {
            Group organization = groupRepository.findById(orgId);
            if (organization == null) {
                logger.warn(String.format(
                        "The backend is try to update the user and organization relationship, but the organization[id=%s] is not found",
                        orgId));
                continue;
            }
            groups.add(organization);
        }

        if (groups.isEmpty()) {
            throw new UserException("应用用户至少应该归属于一个部门");
        }

        appUser.setGroups(groups);
        userRepository.save(appUser);
    }

}
