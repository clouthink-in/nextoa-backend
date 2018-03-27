package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.IdsParam;
import in.clouthink.synergy.account.rest.param.SaveGroupParam;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UsernameSearchParam;
import in.clouthink.synergy.account.rest.support.GroupRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdAndValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/groups", description = "用户组管理")
@RestController
@RequestMapping("/api")
public class GroupRestController {

    @Autowired
    private GroupRestSupport groupRestSupport;

    @ApiOperation(value = "列出根节点的所有用户组节点")
    @GetMapping(value = {"/groups", "/contact/groups"})
    public List<GroupView> listRootGroups() {
        return groupRestSupport.listRootGroups();
    }

    @ApiOperation(value = "列出指定父节点下的所有子用户组节点")
    @GetMapping(value = {"/groups/{id}/children", "/contact/groups/{id}/children"})
    public List<GroupView> listGroupChildren(@PathVariable String id) {
        return groupRestSupport.listGroupChildren(id);
    }

    @ApiOperation(value = "列出指定用户组下的用户,支持分页")
    @GetMapping(value = {"/groups/{id}/users", "/contact/groups/{id}/users"})
    public Page<UserView> listBindUsers(@PathVariable String id,
                                        UsernameSearchParam queryRequest) {
        return groupRestSupport.listBindUsers(id, queryRequest);
    }

    @ApiOperation(value = "新增根节点的用户组")
    @PostMapping(value = "/groups")
    public IdAndValue createGroup(@RequestBody SaveGroupParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(groupRestSupport.createGroup(request, user));
    }

    @ApiOperation(value = "修改指定的用户组基本信息")
    @PostMapping(value = "/groups/{id}")
    public void updateGroup(@PathVariable String id, @Validated @RequestBody SaveGroupParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.updateGroup(id, request, user);
    }

    @ApiOperation(value = "删除指定的用户组（如果子节点不为空不能删除）")
    @DeleteMapping(value = "/groups/{id}")
    public void deleteGroup(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.deleteGroup(id, user);
    }

    @ApiOperation(value = "在指定的用户组节点下增加子节点")
    @PostMapping(value = "/groups/{id}/children")
    public IdAndValue createGroupChild(@PathVariable String id, @Validated @RequestBody SaveGroupParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(groupRestSupport.createGroupChild(id, request, user));
    }

    @ApiOperation(value = "在指定的用户组节点下增加用户")
    @PostMapping(value = "/groups/{id}/users")
    public IdAndValue createUserUnderGroup(@PathVariable String id, @Validated @RequestBody SaveUserParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(groupRestSupport.createUserUnderGroup(id, request, user));
    }

    @ApiOperation(value = "为用户组绑定用户")
    @PostMapping(value = "/groups/{id}/bind-users")
    public void bindGroupAndUsers(@PathVariable String id, @Validated @RequestBody IdsParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.bindGroupAndUsers(id, request.getIds(), user);
    }

    @ApiOperation(value = "为用户组解绑用户")
    @PostMapping(value = "/groups/{id}/unbind-users")
    public void unbindGroupAndUsers(@PathVariable String id, @Validated @RequestBody IdsParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.unbindGroupAndUsers(id, request.getIds(), user);
    }

}
