package base;

import io.restassured.RestAssured;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.ExtentLoggingFilter;


import utils.ConfigManager;
import utils.ExtentTestListener;


@Listeners(ExtentTestListener.class)
public class BaseTest {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigManager.getProperty("baseUrl");
        System.out.println(">>> Using Base URI: [" + baseUrl + "]");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = baseUrl;
        
        RestAssured.filters(new ExtentLoggingFilter());


        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();

        System.out.println("Base URI set to: " + baseUrl);
    }
}
