package tests;

import base.BaseTest;
import io.restassured.response.Response;
import utils.AuthUtil;
import utils.ExtentLogger;
import utils.ExtentTestListener;
import utils.TestDataUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTests extends BaseTest {

	@Test
	public void getUserDetails() {
		Response response = given().header("Content-Type", "application/json").headers(AuthUtil.getApiKeyHeaders())
				.when().get("/api/users/2").then().statusCode(200).body("data.id", equalTo(2))
				.body("data.email", equalTo("janet.weaver@reqres.in")).extract().response();

		int statusCode = response.getStatusCode();

		ExtentLogger.validation("Status Code is 200", statusCode == 200);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void createUser() {
		String requestBody = TestDataUtil.getTestData("createUser.json");

		Response response = given().header("Content-Type", "application/json")
			    .headers(AuthUtil.getApiKeyHeaders())
				.body(requestBody).when().post("/api/users").then().statusCode(201).body("name", equalTo("John Doe"))
				.extract().response();

		int statusCode = response.getStatusCode();

		ExtentLogger.validation("Status Code is 201", statusCode == 201);
		Assert.assertEquals(statusCode, 201);

		String job = response.jsonPath().getString("job");
		ExtentLogger.validation("Job field is 'Engineer'", "Engineer".equals(job));
	}

	@Test
	public void login() {

		String requestBody = TestDataUtil.getTestData("validLogin.json");

		Response response = given().header("Content-Type", "application/json")
			    .headers(AuthUtil.getApiKeyHeaders())
				.body(requestBody).when().post("api/login").then().statusCode(200).extract().response();

		int statusCode = response.getStatusCode();

		ExtentLogger.validation("Status Code is 201", statusCode == 200);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void invalidLogin() {
		String requestBody = TestDataUtil.getTestData("invalidLogin.json");

		Response response = given().header("Content-Type", "application/json")
			    .headers(AuthUtil.getApiKeyHeaders())
				.body(requestBody).when().post("api/login").then().statusCode(400).extract().response();

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);

		ExtentLogger.validation("Status Code is 400", statusCode == 400);

	}
}
