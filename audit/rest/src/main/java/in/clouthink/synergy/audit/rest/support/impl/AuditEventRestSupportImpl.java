package in.clouthink.synergy.audit.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.audit.domain.model.AuditEvent;
import in.clouthink.synergy.audit.rest.support.AuditEventRestSupport;
import in.clouthink.synergy.audit.service.AuditEventService;
import in.clouthink.synergy.audit.rest.dto.AuditEventQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dz
 */
@Component
public class AuditEventRestSupportImpl implements AuditEventRestSupport {

	@Autowired
	private AuditEventService auditEventService;

	@Override
	public Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest) {
		return auditEventService.findAuditEventPage(queryRequest);
	}

	@Override
	public AuditEvent getAuditEventDetail(String id) {
		return auditEventService.findById(id);
	}

	@Override
	public void deleteAuditEventsByDay(String realm, Date day, User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("只有超级管理员能删除用户操作审计数据.");
		}

		auditEventService.deleteAuditEventsByDay(realm, day);
	}

	@Override
	public void deleteAuditEventsBeforeDay(String realm, Date day, User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("只有超级管理员能删除用户操作审计数据.");
		}

		auditEventService.deleteAuditEventsBeforeDay(realm, day);
	}
}
