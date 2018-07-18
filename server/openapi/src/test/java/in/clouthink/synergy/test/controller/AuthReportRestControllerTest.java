package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.audit.domain.model.AuthEventAggregation;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.Test;

public class AuthReportRestControllerTest extends SimpleCrudControllerTest {

    @Override
    protected void testList() throws Exception {
        doGetEntityPageTest("/api/auth-report/by-month", AuthEventAggregation.class);
        doGetEntityPageTest("/api/auth-report/by-day", AuthEventAggregation.class);
    }

}
