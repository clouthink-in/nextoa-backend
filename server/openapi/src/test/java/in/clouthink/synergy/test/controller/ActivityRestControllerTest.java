package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.team.rest.view.ActivityView;
import in.clouthink.synergy.test.common.SimpleCrudControllerTest;
import org.junit.Before;
import org.junit.Test;

public class ActivityRestControllerTest extends SimpleCrudControllerTest {

    @Override
    protected void testList() throws Exception {
        doGetEntityPageTest("/api/activities", ActivityView.class);
    }

}
