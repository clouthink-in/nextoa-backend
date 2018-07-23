package in.clouthink.synergy.test.controller;

import in.clouthink.synergy.test.common.AbstractTest;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class SmsHistoryRestControllerTest extends AbstractTest {

    @Test
    public void testList() throws Exception {
        //SmsHistorySummary.class
        given()
                .when()
                .get("/api/sms-histories")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
