package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.account.rest.view.RoleView;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.Test;

public class ResourceRestControllerTest extends SimpleCrudControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testCrud() throws Exception {
        super.testCrud();
    }

    @Override
    protected void testList() throws Exception {
        doGetEntityListTest("/api/resources/list", ResourceView.class);
        doGetEntityListTest("/api/resources/tree", ResourceView.class);
    }
}
