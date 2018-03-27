package in.clouthink.synergy.audit.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.rest.param.AuditEventSearchParam;
import in.clouthink.synergy.audit.rest.support.AuditEventRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.daas.audit.annotation.Ignored;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * backend用户操作日志
 *
 * @author dz
 */
@Api("系统操作审计日志")
@Ignored
@RestController
@RequestMapping("/api/audit-events")
public class AuditRestController {

    @Autowired
    private AuditEventRestSupport auditEventRestSupport;

    @ApiOperation(value = "审计日志列表,支持分页,支持动态查询(按名称,分类查询)")
    @GetMapping()
    public Page<AuditEvent> listAuditEventPage(AuditEventSearchParam queryRequest) {
        queryRequest.setRealm("backend");
        return auditEventRestSupport.listAuditEventPage(queryRequest);
    }

    @ApiOperation(value = "审计日志明细")
    @GetMapping(value = "/{id}")
    public AuditEvent getAuditEventDetail(@PathVariable String id) {
        return auditEventRestSupport.getAuditEventDetail(id);
    }

    @ApiOperation(value = "删除日志-以天为单位")
    @DeleteMapping(value = "/by-day/{day}")
    public void deleteAuditEventsByDay(@PathVariable Date day) {
        User user = (User) SecurityContexts.getContext().requireUser();
        auditEventRestSupport.deleteAuditEventsByDay("backend", day, user);
    }

    @ApiOperation(value = "删除日志-删除指定日期（不包括）之前的所有数据")
    @DeleteMapping(value = "/before-day/{day}")
    public void deleteAuditEventsBeforeDay(@PathVariable Date day) {
        User user = (User) SecurityContexts.getContext().requireUser();
        auditEventRestSupport.deleteAuditEventsBeforeDay("backend", day, user);
    }

}
