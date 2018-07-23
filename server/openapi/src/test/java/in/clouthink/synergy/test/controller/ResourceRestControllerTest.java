package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.test.common.AbstractTest;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class ResourceRestControllerTest extends AbstractTest {

    @Test
    public void testList() throws Exception {
        //ResourceView
        given()
                .get("/api/resources/list")
                .then()
                .assertThat()
                .statusCode(200);
        given()
                .get("/api/resources/tree")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
