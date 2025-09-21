package base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import utils.ConfigManager;

public class BaseTest {

	@BeforeClass
	public void setup() {
		String baseUrl = ConfigManager.getProperty("baseUrl");
		RestAssured.baseURI = baseUrl;

		System.out.println("Base URI set to: " + baseUrl);
	}

}
