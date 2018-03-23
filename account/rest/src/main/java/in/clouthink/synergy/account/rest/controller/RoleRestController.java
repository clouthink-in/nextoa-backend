package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.IdsParam;
import in.clouthink.synergy.account.rest.param.RoleSearchParam;
import in.clouthink.synergy.account.rest.param.SaveRoleParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.support.RoleRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdAndValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("角色管理")
@RestController
@RequestMapping("/api")
public class RoleRestController {

    @Autowired
    private RoleRestSupport roleRestSupport;

    @ApiOperation(value = "获取角色列表（分页）")
    @GetMapping(value = "/roles")
    public Page<RoleView> getRoles(RoleSearchParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return roleRestSupport.getRoles(request, user);
    }

    @ApiOperation(value = "获取指定角色已绑定的用户列表（分页）")
    @GetMapping(value = "/roles/{id}/users")
    public Page<UserView> getBindUsers(@PathVariable String id, UserSearchParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return roleRestSupport.getBindUsers(id, request, user);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/roles")
    public IdAndValue createRole(@Validated @RequestBody SaveRoleParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(roleRestSupport.createRole(request, user).getId());
    }

    @ApiOperation(value = "更新角色")
    @PostMapping(value = "/roles/{id}")
    public void updateRole(@PathVariable String id, @Validated @RequestBody SaveRoleParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        roleRestSupport.updateRole(id, request, user);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/roles/{id}")
    public void deleteRole(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        roleRestSupport.deleteRole(id, user);
    }

    @ApiOperation(value = "绑定用户到指定角色")
    @PostMapping(value = "/roles/{id}/bindUsers")
    public void bindRoleUsers(@PathVariable String id, @Validated @RequestBody IdsParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        roleRestSupport.bindRoleAndUsers(id, request.getIds(), user);
    }

    @ApiOperation(value = "从指定角色解绑用户")
    @PostMapping(value = "/roles/{id}/unbindUsers")
    public void unbindRoleUsers(@PathVariable String id, @Validated @RequestBody IdsParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        roleRestSupport.unbindRoleAndUsers(id, request.getIds(), user);
    }

}
