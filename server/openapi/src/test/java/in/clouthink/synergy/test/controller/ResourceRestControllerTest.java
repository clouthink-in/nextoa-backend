package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.rbac.rest.view.ResourceView;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;

public class ResourceRestControllerTest extends SimpleCrudControllerTest {

    @Override
    protected void testList() throws Exception {
        doGetEntityListTest("/api/resources/list", ResourceView.class);
        doGetEntityListTest("/api/resources/tree", ResourceView.class);
    }

}
