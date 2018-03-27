package in.clouthink.synergy.audit.rest.controller;

import in.clouthink.synergy.audit.domain.model.AuthEventAggregation;
import in.clouthink.synergy.audit.rest.support.AuthReportRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.daas.audit.annotation.Ignored;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/api/auth-report", description = "系统登录认证报表")
@Ignored
@RestController
@RequestMapping("/api/auth-report")
public class AuthReportController {

    @Autowired
    private AuthReportRestSupport authReportRestSupport;

    @ApiOperation(value = "审计报表列表（月）,支持分页")
    @GetMapping(value = "/by-month")
    public Page<AuthEventAggregation> listAuthReportByMonth(PageSearchParam queryRequest) {
        return authReportRestSupport.listAuthReportByMonth("backend", queryRequest);
    }

    @ApiOperation(value = "审计报表列表（日）,支持分页")
    @GetMapping(value = "/by-day")
    public Page<AuthEventAggregation> listAuthReportByDay(PageSearchParam queryRequest) {
        return authReportRestSupport.listAuthReportByDay("backend", queryRequest);
    }

}
