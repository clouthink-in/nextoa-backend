package in.clouthink.synergy.audit.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.audit.domain.model.AuthEvent;
import in.clouthink.synergy.audit.rest.param.AuthEventSearchParam;
import in.clouthink.synergy.audit.rest.support.AuthEventRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * backend登录日志
 */
@Api("系统登录认证日志")
@RestController
@RequestMapping("/api/auth-events")
public class AuthRestController {

    @Autowired
    private AuthEventRestSupport authEventRestSupport;

    @ApiOperation(value = "用户登录日志列表,支持分页,支持动态查询(按名称,状态查询)")
    @GetMapping()
    public Page<AuthEvent> listAuthEventPage(AuthEventSearchParam queryRequest) {
        queryRequest.setRealm("backend");
        return authEventRestSupport.listAuthEventPage(queryRequest);
    }

    @ApiOperation(value = "用户登录日志详情")
    @GetMapping(value = "/{id}")
    public AuthEvent getAuthEventDetail(@PathVariable String id) {
        return authEventRestSupport.getAuthEventDetail(id);
    }

    @ApiOperation(value = "删除日志-以天为单位")
    @DeleteMapping(value = "/byDay/{day}")
    public void deleteAuditEventsByDay(@PathVariable Date day) {
        User user = (User) SecurityContexts.getContext().requireUser();
        authEventRestSupport.deleteAuthEventsByDay("backend", day, user);
    }

    @ApiOperation(value = "删除日志-删除指定日期（不包括）之前的所有数据")
    @DeleteMapping(value = "/beforeDay/{day}")
    public void deleteAuditEventsBeforeDay(@PathVariable Date day) {
        User user = (User) SecurityContexts.getContext().requireUser();
        authEventRestSupport.deleteAuthEventsBeforeDay("backend", day, user);
    }

}
