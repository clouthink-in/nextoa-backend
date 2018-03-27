package in.clouthink.synergy.audit.rest.controller;

import in.clouthink.daas.audit.annotation.Ignored;
import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.audit.rest.support.AuditReportRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/audit-report", description = "系统操作审计报表")
@Ignored
@RestController
@RequestMapping("/api/audit-report")
public class AuditReportController {

    @Autowired
    private AuditReportRestSupport auditReportRestSupport;

    @ApiOperation(value = "审计报表列表（月）,支持分页")
    @GetMapping(value = "/by-month")
    public Page<AuditEventAggregation> listAuditReportByMonth(PageSearchParam queryRequest) {
        return auditReportRestSupport.listAuditReportByMonth("backend", queryRequest);
    }

    @ApiOperation(value = "审计报表列表（日）,支持分页")
    @GetMapping(value = "/by-day")
    public Page<AuditEventAggregation> listAuditReportByDay(PageSearchParam queryRequest) {
        return auditReportRestSupport.listAuditReportByDay("backend", queryRequest);
    }

}
