package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.test.common.AbstractTest;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class AuthRestControllerTest extends AbstractTest {

    @Test
    public void testList() throws Exception {
        given()
                .get("/api/auth-events")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
