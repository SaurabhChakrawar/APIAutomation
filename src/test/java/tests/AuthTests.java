package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AuthUtil;

public class AuthTests extends BaseTest {

//    @Test
//    public void testApiKeyAuth() {
//        int statusCode = RestAssured
//            .given()
//                .headers(AuthUtil.getApiKeyHeaders())
//            .when()
//                .get("/secure/resource")
//            .then()
//                .extract().statusCode();
//
//        Assert.assertEquals(statusCode, 200, "API Key auth failed!");
//    }
//
//    @Test
//    public void testBearerTokenAuth() {
//        int statusCode = RestAssured
//            .given()
//                .headers(AuthUtil.getBearerHeaders())
//            .when()
//                .get("/user/profile")
//            .then()
//                .extract().statusCode();
//
//        Assert.assertEquals(statusCode, 200, "Bearer token auth failed!");
//    }
//
//    @Test
//    public void testOAuth2Auth() {
//        int statusCode = RestAssured
//            .given()
//                .headers(AuthUtil.getOAuth2Headers())
//            .when()
//                .get("/oauth2/secure-data")
//            .then()
//                .extract().statusCode();
//
//        Assert.assertEquals(statusCode, 200, "OAuth2 auth failed!");
//    }
}
