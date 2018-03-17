package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.GroupRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("组织机构及用户管理")
@RestController
@RequestMapping("/api")
public class GroupRestController {

    @Autowired
    private GroupRestSupport groupRestSupport;

    @ApiOperation(value = "列出根节点的所有组织机构节点")
    @RequestMapping(value = {"/groups", "/contact/groups"}, method = RequestMethod.GET)
    public List<GroupSummary> listRootOrgainizations() {
        return groupRestSupport.listRootGroups();
    }

    @ApiOperation(value = "列出指定父节点下的所有子组织机构节点")
    @RequestMapping(value = {"/groups/{id}/children",
            "/contact/groups/{id}/children"}, method = RequestMethod.GET)
    public List<GroupSummary> listOrgainizationChildren(@PathVariable String id) {
        return groupRestSupport.listGroupChildren(id);
    }

    @ApiOperation(value = "列出指定组织机构下的用户,支持分页")
    @RequestMapping(value = {"/groups/{id}/users",
            "/contact/groups/{id}/users"}, method = RequestMethod.GET)
    public Page<UserSummary> listAppUsersOfGroup(@PathVariable String id,
                                                 UsernamePageQueryParameter queryRequest) {
        return groupRestSupport.listUsersOfGroup(id, queryRequest);
    }

    @ApiOperation(value = "新增根节点的组织机构")
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public String createGroup(@RequestBody SaveGroupParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return groupRestSupport.createGroup(request, user);
    }

    @ApiOperation(value = "修改指定的组织结构基本信息")
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.POST)
    public void updateGroup(@PathVariable String id, @RequestBody SaveGroupParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.updateGroup(id, request, user);
    }

    @ApiOperation(value = "删除指定的组织机构（如果子节点不为空不能删除）")
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        groupRestSupport.deleteGroup(id, user);
    }

    @ApiOperation(value = "在指定的组织机构节点下增加子节点")
    @RequestMapping(value = "/groups/{id}/children", method = RequestMethod.POST)
    public String createGroupChild(@PathVariable String id, @RequestBody SaveGroupParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return groupRestSupport.createGroupChild(id, request, user);
    }

    @ApiOperation(value = "在指定的组织机构节点下增加用户")
    @RequestMapping(value = "/groups/{id}/users", method = RequestMethod.POST)
    public String createAppUser(@PathVariable String id, @RequestBody SaveUserParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return groupRestSupport.createAppUser(id, request, user);
    }

}
