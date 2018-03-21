package in.clouthink.synergy.account.service.impl;

import in.clouthink.synergy.account.domain.model.*;
import in.clouthink.synergy.account.domain.request.RoleQueryRequest;
import in.clouthink.synergy.account.domain.request.SaveRoleRequest;
import in.clouthink.synergy.account.domain.request.UserQueryRequest;
import in.clouthink.synergy.account.exception.RoleException;
import in.clouthink.synergy.account.exception.RoleNotFoundException;
import in.clouthink.synergy.account.exception.UserException;
import in.clouthink.synergy.account.exception.UserNotFoundException;
import in.clouthink.synergy.account.repository.RoleRepository;
import in.clouthink.synergy.account.repository.UserRepository;
import in.clouthink.synergy.account.repository.UserRoleRelationshipRepository;
import in.clouthink.synergy.account.service.RoleService;
import in.clouthink.synergy.account.spi.RoleReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRelationshipRepository relationshipRepository;

    @Autowired(required = false)
    private List<RoleReference> roleReferenceList;

    @Override
    public Role requireSysAdminRole() {
        Role result = roleRepository.findFirstByCode(Roles.ADMIN_ROLE_NAME);
        if (result == null) {
            throw new RoleNotFoundException("ROLE_ADMIN not found");
        }
        if (RoleType.SYS_ROLE != result.getType()) {
            throw new RoleException("ROLE_ADMIN found but not a SYS_ROLE");
        }
        return result;
    }

    @Override
    public Role requireSysMgrRole() {
        Role result = roleRepository.findFirstByCode(Roles.MGR_ROLE_NAME);
        if (result == null) {
            throw new RoleNotFoundException("ROLE_MGR not found");
        }
        if (RoleType.SYS_ROLE != result.getType()) {
            throw new RoleException("ROLE_MGR found but not a SYS_ROLE");
        }
        return result;
    }

    @Override
    public Role requireSysUserRole() {
        Role result = roleRepository.findFirstByCode(Roles.USER_ROLE_NAME);
        if (result == null) {
            throw new RoleNotFoundException("ROLE_USER not found");
        }
        if (RoleType.SYS_ROLE != result.getType()) {
            throw new RoleException("ROLE_USER found but not a SYS_ROLE");
        }
        return result;
    }

    @Override
    public Page<Role> listRoles(RoleQueryRequest roleQueryRequest) {
        return roleRepository.queryPage(roleQueryRequest);
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findByCode(String code) {
        if (code == null) {
            return null;
        }

        return roleRepository.findFirstByCode(code.toUpperCase());
    }

    @Override
    public Role createRole(SaveRoleRequest request, RoleType type, User byWho) {
        if (null == type) {
            throw new RoleException("角色类型不能为空");
        }

        if (StringUtils.isEmpty(request.getCode())) {
            throw new RoleException("角色编码不能为空");
        }

        if (request.getCode().toUpperCase().startsWith("ROLE_")) {
            throw new RoleException("角色编码不需要以ROLE_作为前缀");
        }

        if (RoleType.SYS_ROLE != type && Roles.isIllegal(request.getCode())) {
            throw new RoleException("不能使用内置角色编码");
        }

        if (StringUtils.isEmpty(request.getName())) {
            throw new RoleException("角色名称不能为空");
        }

        Role roleByCode = roleRepository.findFirstByCode(request.getCode().toLowerCase());
        if (roleByCode != null) {
            throw new RoleException("角色编码不能重复");
        }

        Role roleByName = roleRepository.findFirstByName(request.getName());
        if (roleByName != null) {
            throw new RoleException("角色名称不能重复");
        }

        Role role = new Role();
        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setType(type);
        role.setCreatedAt(new Date());
        role.setModifiedAt(new Date());
        return roleRepository.save(role);
    }

    @Override
    public void updateRole(String id, SaveRoleRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new RoleException("角色名称不能为空");
        }

        Role target = findById(id);
        if (target == null) {
            throw new RoleNotFoundException(id);
        }

        if (Roles.isSysRole(target)) {
            throw new RoleException("不能编辑内置系统角色");
        }

        Role roleByName = roleRepository.findFirstByName(request.getName());
        if (roleByName != null && !roleByName.getId().equals(target.getId())) {
            throw new RoleException("角色名称不能重复");
        }

        target.setName(request.getName());
        target.setDescription(request.getDescription());
        target.setModifiedAt(new Date());
        roleRepository.save(target);
    }

    @Override
    public void deleteRole(String id, User byWho) {
        Role role = roleRepository.findById(id);
        if (role == null) {
            return;
        }

        if (Roles.isSysRole(role)) {
            throw new RoleException("不能删除内置系统角色");
        }

        if (relationshipRepository.findFirstByRole(role) != null) {
            throw new RoleException("该角色下已绑定用户,请解除和用户的关系后再进行删除角色操作");
        }

        if (roleReferenceList != null) {
            roleReferenceList.forEach(ref -> {
                if (ref.hasReference(role)) {
                    throw new ValidationException("该数据已经被其他数据引用,不能删除");
                }
            });
        }

        roleRepository.delete(role);
    }

    @Override
    public Page<User> listBindUsers(String roleId, UserQueryRequest request) {
        Role role = findById(roleId);
        if (role == null) {
            throw new RoleNotFoundException();
        }
        Page<UserRoleRelationship> relationships = relationshipRepository.findByRole(role,
                                                                                     new PageRequest(request.getStart(),
                                                                                                     request.getLimit()));
        return new PageImpl<>(relationships.getContent()
                                           .stream()
                                           .map(UserRoleRelationship::getUser)
                                           .collect(Collectors.toList()),
                              new PageRequest(request.getStart(), request.getLimit()),
                              relationships.getTotalElements());
    }

    @Override
    public List<Role> listBindRoles(User user) {
        return relationshipRepository.findListByUser(user).stream().map(r -> r.getRole()).collect(Collectors.toList());
    }

    @Override
    public void bindRoleAndUsers(String roleId, String[] userIds) {
        Role role = findById(roleId);
        if (role == null) {
            throw new RoleNotFoundException(roleId);
        }
        Stream.of(userIds).forEach(userId -> {
            User user = userRepository.findById(userId);
            if (user == null) {
                throw new UserNotFoundException(userId);
            }
            if (Users.isAdministrator(user)) {
                throw new UserException("禁止修改超级管理员用户的角色信息.");
            }
            tryRelationship(user, role);
        });
    }

    @Override
    public void unbindRoleAndUsers(String roleId, String[] userIds) {
        Role role = findById(roleId);
        if (role == null) {
            throw new RoleNotFoundException(roleId);
        }
        Stream.of(userIds).forEach(userId -> {
            User user = userRepository.findById(userId);
            if (user == null) {
                return;
            }
            if (Users.isAdministrator(user)) {
                throw new UserException("禁止修改超级管理员用户的角色信息.");
            }
            UserRoleRelationship relationship = relationshipRepository.findByUserAndRole(user, role);
            if (relationship != null) {
                relationshipRepository.delete(relationship);
            }
        });
    }

    @Override
    public void bindUserAndRoles(String userId, String[] roleIds) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        if (Users.isAdministrator(user)) {
            throw new UserException("禁止修改超级管理员用户的角色信息.");
        }

        Stream.of(roleIds).forEach(roleId -> {
            Role role = roleRepository.findById(roleId);
            if (role == null) {
                throw new RoleNotFoundException(roleId);
            }

            tryRelationship(user, role);
        });
    }

    @Override
    public void unbindUserAndRoles(String userId, String[] roleIds) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        if (Users.isAdministrator(user)) {
            throw new UserException("禁止修改超级管理员用户的角色信息.");
        }

        Stream.of(roleIds).forEach(roleId -> {
            Role role = roleRepository.findById(roleId);
            if (role == null) {
                return;
            }

            UserRoleRelationship relationship = relationshipRepository.findByUserAndRole(user, role);
            if (relationship != null) {
                relationshipRepository.delete(relationship);
            }
        });
    }

    //*************************************************
    // private
    //*************************************************

    private void tryRelationship(User user, Role role) {
        UserRoleRelationship relationship = relationshipRepository.findByUserAndRole(user, role);
        if (relationship == null) {
            relationship = new UserRoleRelationship();
            relationship.setRole(role);
            relationship.setUser(user);
            relationship.setCreatedAt(new Date());
            relationshipRepository.save(relationship);
        }
    }

}
