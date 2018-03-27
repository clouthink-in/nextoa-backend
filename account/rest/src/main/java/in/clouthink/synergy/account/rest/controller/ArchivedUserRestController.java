package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.rest.view.UserDetailView;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.view.UserView;
import in.clouthink.synergy.account.rest.support.ArchivedUserRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(value = "/api/archived-users", description = "已归档的用户管理")
@RestController
@RequestMapping("/api/archived-users")
public class ArchivedUserRestController {

    @Autowired
    private ArchivedUserRestSupport archivedUserRestSupport;

    @ApiOperation(value = "查看归档用户列表,支持分页,支持动态查询（用户名等）")
    @GetMapping()
    public Page<UserView> listArchivedUsers(UserSearchParam queryRequest) {
        return archivedUserRestSupport.listArchivedUsers(queryRequest);
    }

    @ApiOperation(value = "查看归档用户基本信息")
    @GetMapping(value = "/{id}")
    public UserDetailView getArchivedUserDetail(@PathVariable String id) {
        return archivedUserRestSupport.getArchivedUser(id);
    }

}
