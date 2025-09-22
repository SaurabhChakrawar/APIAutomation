package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTests extends BaseTest {

    @Test
    public void testUserSchemaValidation() {
        RestAssured
            .given()
                .header("Authorization", "Bearer dummy-token") // replace with AuthUtil.getBearerHeaders()
            .when()
                .get("/user/profile")
            .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }
}
