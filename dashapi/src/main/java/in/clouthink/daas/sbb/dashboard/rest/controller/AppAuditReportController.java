package in.clouthink.daas.sbb.dashboard.rest.controller;

import in.clouthink.daas.sbb.audit.domain.model.AuditEventAggregation;
import in.clouthink.daas.sbb.dashboard.rest.support.AuditReportRestSupport;
import in.clouthink.daas.sbb.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.daas.audit.annotation.Ignored;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dz
 */
@Ignored
@RestController
@RequestMapping("/api")
public class AppAuditReportController {

	@Autowired
	private AuditReportRestSupport auditReportRestSupport;

	@ApiOperation(value = "审计报表列表（月）,支持分页")
	@RequestMapping(value = "/appAuditReports/byMonth", method = RequestMethod.GET)
	public Page<AuditEventAggregation> listAuditReportByMonth(PageQueryParameter queryRequest) {
		return auditReportRestSupport.listAuditReportByMonth("frontend", queryRequest);
	}

	@ApiOperation(value = "审计报表列表（日）,支持分页")
	@RequestMapping(value = "/appAuditReports/byDay", method = RequestMethod.GET)
	public Page<AuditEventAggregation> listAuditReportByDay(PageQueryParameter queryRequest) {
		return auditReportRestSupport.listAuditReportByDay("frontend", queryRequest);
	}

}