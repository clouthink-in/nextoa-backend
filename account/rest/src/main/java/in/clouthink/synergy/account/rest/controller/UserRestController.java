package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.IdsParam;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.support.UserRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdAndValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/users", description = "用户管理")
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserRestSupport userRestSupport;

    @ApiOperation(value = "用户列表,支持分页,支持动态查询（用户名等）")
    @GetMapping()
    public Page<UserView> listUsers(UserSearchParam queryRequest) {
        return userRestSupport.listUsers(queryRequest);
    }

    @ApiOperation(value = "查看用户详情")
    @GetMapping(value = "/{id}")
    public UserDetailView getUserDetail(@PathVariable String id) {
        return userRestSupport.getUserDetail(id);
    }

    @ApiOperation(value = "新增用户（基本资料部分）")
    @PostMapping()
    public IdAndValue createUser(@RequestBody SaveUserParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(userRestSupport.createUser(request, user).getId());
    }

    @ApiOperation(value = "修改用户基本资料")
    @PostMapping(value = "/{id}")
    public void updateUser(@PathVariable String id, @RequestBody SaveUserParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.updateUser(id, request, user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.deleteUser(id, user);
    }

    @ApiOperation(value = "修改用户密码")
    @PostMapping(value = "/{id}/password")
    public void changePassword(@PathVariable String id, @RequestBody ChangePasswordRequest request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.changePassword(id, request, user);
    }

    @ApiOperation(value = "启用用户")
    @PostMapping(value = "/{id}/enable")
    public void enableUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.enableUser(id, user);
    }

    @ApiOperation(value = "禁用用户,用户被禁用后不能使用系统")
    @PostMapping(value = "/{id}/disable")
    public void disableUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.disableUser(id, user);
    }

    @ApiOperation(value = "锁定用户,用户被锁定后不能使用系统")
    @PostMapping(value = "/{id}/lock")
    public void lockUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.lockUser(id, user);
    }

    @ApiOperation(value = "取消锁定用户")
    @PostMapping(value = "/{id}/unlock")
    public void unlockUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.unlockUser(id, user);
    }

    @ApiOperation(value = "查看用户所属用户组")
    @GetMapping(value = "/{userId}/groups")
    public List<GroupWithPath> listBindGroups(@PathVariable String userId) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return userRestSupport.listBindGroups(userId);
    }

    @ApiOperation(value = "设置用户所属用户组（添加）")
    @PostMapping(value = "/{userId}/bind-groups")
    public void bindUserAndGroups(@PathVariable String userId,
                                  @RequestBody IdsParam idsParam) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.bindUserAndGroups(userId, idsParam.getIds(), user);
    }

    @ApiOperation(value = "设置用户所属用户组（取消）")
    @PostMapping(value = "/{userId}/unbind-groups")
    public void unbindUserAndGroups(@PathVariable String userId,
                                    @RequestBody IdsParam idsParam) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.unbindUserAndGroups(userId, idsParam.getIds(), user);
    }

    @ApiOperation(value = "查看用户绑定角色")
    @GetMapping(value = "/{userId}/roles")
    public List<RoleView> listBindRoles(@PathVariable String userId) {
        return userRestSupport.listBindRoles(userId);
    }

    @ApiOperation(value = "设置用户的角色（添加）")
    @PostMapping(value = "/{userId}/bind-roles")
    public void bindUserAndRoles(@PathVariable String userId,
                                 @RequestBody IdsParam idsParam) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.bindUserAndRoles(userId, idsParam.getIds(), user);
    }

    @ApiOperation(value = "设置用户的角色（取消）")
    @PostMapping(value = "/{userId}/unbind-roles")
    public void unbindUserAndRoles(@PathVariable String userId,
                                   @RequestBody IdsParam idsParam) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.unbindUserAndRoles(userId, idsParam.getIds(), user);
    }
}
