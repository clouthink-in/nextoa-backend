package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.Test;

public class AuditReportRestControllerTest extends SimpleCrudControllerTest {

    @Override
    protected void testList() throws Exception {
        doGetEntityPageTest("/api/audit-report/by-month", AuditEventAggregation.class);
        doGetEntityPageTest("/api/audit-report/by-day", AuditEventAggregation.class);
    }
}
