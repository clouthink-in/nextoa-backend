package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.UserRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdValuePair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Api("系统用户管理")
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRestSupport userRestSupport;

    @ApiOperation(value = "系统用户列表,支持分页,支持动态查询（用户名等）")
    @GetMapping(value = "/users")
    public Page<UserSummary> listSysUsers(UserQueryParameter queryRequest) {
        return userRestSupport.listUsers(queryRequest);
    }

    @ApiOperation(value = "查看系统用户详情")
    @GetMapping(value = "/users/{id}")
    public UserDetail getSysUserDetail(@PathVariable String id) {
        return userRestSupport.getUserDetail(id);
    }

    @ApiOperation(value = "新增系统用户（基本资料部分）")
    @PostMapping(value = "/users")
    public IdValuePair createSysUser(@RequestBody SaveUserParameter request) {
        return IdValuePair.from(userRestSupport.createUser(request).getId());
    }

    @ApiOperation(value = "修改系统用户基本资料")
    @PostMapping(value = "/users/{id}")
    public void updateSysUser(@PathVariable String id, @RequestBody SaveUserParameter request) {
        userRestSupport.updateUser(id, request);
    }

    @ApiOperation(value = "删除系统用户")
    @DeleteMapping(value = "/users/{id}")
    public void deleteSysUser(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        userRestSupport.deleteUser(id, user);
    }

    @ApiOperation(value = "修改系统用户密码")
    @PostMapping(value = "/users/{id}/password")
    public void changePassword(@PathVariable String id, @RequestBody ChangePasswordRequest request) {
        userRestSupport.changePassword(id, request);
    }

    @ApiOperation(value = "启用系统用户")
    @PostMapping(value = "/users/{id}/enable")
    public void enableSysUser(@PathVariable String id) {
        userRestSupport.enableUser(id);
    }

    @ApiOperation(value = "禁用系统用户,用户被禁用后不能使用系统")
    @PostMapping(value = "/users/{id}/disable")
    public void disableSysUser(@PathVariable String id) {
        userRestSupport.disableUser(id);
    }

    @ApiOperation(value = "锁定系统用户,用户被锁定后不能使用系统")
    @PostMapping(value = "/users/{id}/lock")
    public void lockSysUser(@PathVariable String id) {
        userRestSupport.lockUser(id);
    }

    @ApiOperation(value = "取消锁定系统用户")
    @PostMapping(value = "/users/{id}/unlock")
    public void unlockSysUser(@PathVariable String id) {
        userRestSupport.unlockUser(id);
    }

}
