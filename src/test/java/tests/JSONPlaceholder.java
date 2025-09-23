package tests;

import base.BaseTest;
import io.restassured.response.Response;
import utils.ExtentLogger;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JSONPlaceholder extends BaseTest {

	@Test(groups = "jsonplaceholder")
	public void getPosts() {

		Response response = given().header("Content-type", "application/json").when().get("/posts").then()
				.statusCode(200).extract().response();

		int statusCode = response.statusCode();
		ExtentLogger.validation("Status Code is 200", statusCode == 200);
		Assert.assertEquals(200, statusCode);
		
		
	}
}
