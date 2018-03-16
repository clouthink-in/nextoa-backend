package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.rest.dto.UserDetail;
import in.clouthink.synergy.account.rest.dto.UserQueryParameter;
import in.clouthink.synergy.account.rest.dto.UserSummary;
import in.clouthink.synergy.account.rest.support.ArchivedUserRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api("已归档的用户管理")
@RestController
@RequestMapping("/api")
public class ArchivedUserRestController {

    @Autowired
    private ArchivedUserRestSupport archivedUserRestSupport;

    @ApiOperation(value = "查看归档用户列表,支持分页,支持动态查询（用户名等）")
    @GetMapping(value = "/archivedUsers")
    public Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest) {
        return archivedUserRestSupport.listArchivedUsers(queryRequest);
    }

    @ApiOperation(value = "查看归档用户基本信息")
    @GetMapping(value = "/archivedUsers/{id}")
    public UserDetail getArchivedUser(@PathVariable String id) {
        return archivedUserRestSupport.getArchivedUser(id);
    }

}
