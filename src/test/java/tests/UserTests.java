package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTests extends BaseTest {

    @Test
    public void getUserDetails() {
        Response response =
                given()
                        .header("Content-Type", "application/json")
                .when()
                        .get("/api/users/2")
                .then()
                        .statusCode(200)
                        .body("data.id", equalTo(2))
                        .body("data.email", equalTo("janet.weaver@reqres.in"))
                        .extract().response();

        System.out.println("Response Body: " + response.asString());
    }

    @Test
    public void createUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"Engineer\" }";

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                .when()
                        .post("/api/users")
                .then()
                        .statusCode(201)
                        .body("name", equalTo("John"))
                        .extract().response();

        System.out.println("User Created: " + response.asString());
    }
}
