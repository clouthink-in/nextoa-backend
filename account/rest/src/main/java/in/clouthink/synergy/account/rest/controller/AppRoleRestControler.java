package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.AppRoleRestSupport;
import in.clouthink.synergy.shared.domain.model.IdValuePair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("应用扩展角色管理")
@RestController
@RequestMapping("/api")
public class AppRoleRestControler {

    @Autowired
    private AppRoleRestSupport appRoleRestSupport;

    @ApiOperation(value = "获取新增角色")
    @GetMapping(value = "/approles")
    public Page<RoleSummary> getAppRoles(RoleQueryParameter request) {
        return appRoleRestSupport.getAppRoles(request);
    }

    @ApiOperation(value = "获取新增角色(不分页)")
    @GetMapping(value = "/approles/list")
    public List<RoleSummary> getAppRolesList() {
        return appRoleRestSupport.getAppRolesList();
    }

    @ApiOperation(value = "获取新增角色对应用户")
    @GetMapping(value = "/approles/{id}/users")
    public Page<UserSummary> getUsersByAppRoleId(@PathVariable String id, UserQueryParameter request) {
        return appRoleRestSupport.getUsersByAppRoleId(id, request);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/approles")
    public IdValuePair createAppRole(@RequestBody SaveRoleParameter request) {
        return IdValuePair.from(appRoleRestSupport.createAppRole(request).getId());
    }

    @ApiOperation(value = "更新角色")
    @PostMapping(value = "/approles/{id}")
    public void updateAppRole(@PathVariable String id, @RequestBody SaveRoleParameter request) {
        appRoleRestSupport.updateAppRole(id, request);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/approles/{id}")
    public void deleteAppRole(@PathVariable String id) {
        appRoleRestSupport.deleteAppRole(id);
    }

    @ApiOperation(value = "为角色绑定用户")
    @PostMapping(value = "/approles/{id}/bindUsers")
    public void bindUsers4AppRole(@PathVariable String id, @RequestBody UsersForRoleParameter request) {
        appRoleRestSupport.bindUsers4AppRole(id, request);
    }

    @ApiOperation(value = "为角色解绑用户")
    @PostMapping(value = "/approles/{id}/unBindUsers")
    public void unBindUsers4AppRole(@PathVariable String id, @RequestBody UsersForRoleParameter request) {
        appRoleRestSupport.unBindUsers4AppRole(id, request);
    }

}
