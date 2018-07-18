package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.account.rest.view.GroupView;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.Test;

public class GroupRestControllerTest extends SimpleCrudControllerTest {

    @Override
    protected void testList() throws Exception {
        doGetEntityPageTest("/api/users", GroupView.class);
    }

}
