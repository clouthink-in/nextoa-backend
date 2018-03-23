package in.clouthink.synergy.audit.rest.support.impl;

import in.clouthink.synergy.audit.domain.model.AggregationType;
import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.audit.repository.AuditEventAggregationRepository;
import in.clouthink.synergy.audit.rest.support.AuditReportRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class AuditReportRestSupportImpl implements AuditReportRestSupport {

	@Autowired
	private AuditEventAggregationRepository auditEventAggregationRepository;

	@Override
	public Page<AuditEventAggregation> listAuditReportByMonth(String realm, PageSearchParam queryRequest) {
		return auditEventAggregationRepository.findPageByRealmAndAggregationTypeOrderByAggregationKeyDesc(realm,
																										  AggregationType.MONTH,
																										  new PageRequest(
																												  queryRequest
																														  .getStart(),
																												  queryRequest
																														  .getLimit()));
	}

	@Override
	public Page<AuditEventAggregation> listAuditReportByDay(String realm, PageSearchParam queryRequest) {
		return auditEventAggregationRepository.findPageByRealmAndAggregationTypeOrderByAggregationKeyDesc(realm,
																										  AggregationType.DAY,
																										  new PageRequest(
																												  queryRequest
																														  .getStart(),
																												  queryRequest
																														  .getLimit()));
	}

}
