package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.test.common.AbstractTest;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class AuditRestControllerTest extends AbstractTest {

    @Test
    public void testList() throws Exception {
        //UserView.class
        given()
                .get("/api/audit-events")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
