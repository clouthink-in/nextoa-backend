package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.RoleSummary;
import in.clouthink.synergy.account.rest.dto.UserQueryParameter;
import in.clouthink.synergy.account.rest.dto.UserSummary;
import in.clouthink.synergy.account.rest.dto.UsersForRoleParameter;
import in.clouthink.synergy.account.rest.support.SysRoleRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("系统内置角色管理")
@RestController
@RequestMapping("/api")
public class SysRoleRestControler {

    @Autowired
    private SysRoleRestSupport sysRoleRestSupport;

    @ApiOperation(value = "获取内置角色（角色管理）")
    @GetMapping(value = "/sysroles")
    public List<RoleSummary> getSysRoles() {
        User user = (User) SecurityContexts.getContext().requireUser();
        return sysRoleRestSupport.getSysRoles(user);
    }

    @ApiOperation(value = "获取内置角色(权限管理)")
    @GetMapping(value = "/sysroles/privilege")
    public List<RoleSummary> getSysRoles4Privilege() {
        User user = (User) SecurityContexts.getContext().requireUser();
        return sysRoleRestSupport.getSysRoles4Privilege(user);
    }

    @ApiOperation(value = "获取内置角色对应用户")
    @GetMapping(value = "/sysroles/{id}/users")
    public Page<UserSummary> getUsersBySysRoleId(@PathVariable String id, UserQueryParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return sysRoleRestSupport.getUsersBySysRoleId(id, request, user);
    }

    @ApiOperation(value = "为角色绑定用户")
    @PostMapping(value = "/sysroles/{id}/bindUsers")
    public void bindUsers4SysRole(@PathVariable String id, @RequestBody UsersForRoleParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        sysRoleRestSupport.bindUsers4SysRole(id, request, user);
    }

    @ApiOperation(value = "为角色解绑用户")
    @PostMapping(value = "/sysroles/{id}/unBindUsers")
    public void unBindUsers4SysRole(@PathVariable String id, @RequestBody UsersForRoleParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        sysRoleRestSupport.unBindUsers4SysRole(id, request, user);
    }

}
