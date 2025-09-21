package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtil {
    private static String token = ConfigManager.getProperty("accessToken");

    // Attach Bearer Token automatically
    public static String getBearerToken() {
        if (token == null || token.isEmpty()) {
            refreshToken(); // fetch if missing
        }
        return token;
    }

    // Refresh Token logic
    public static void refreshToken() {
        System.out.println("🔑 Refreshing auth token...");

        Response response = given()
                .formParam("client_id", ConfigManager.getProperty("clientId"))
                .formParam("client_secret", ConfigManager.getProperty("clientSecret"))
                .formParam("grant_type", "client_credentials")
        .when()
                .post(ConfigManager.getProperty("baseUrl") + "/oauth/token");

        token = response.jsonPath().getString("access_token");

        // Save new token in config
        ConfigManager.setProperty("accessToken", token);

        System.out.println("✅ New token stored: " + token);
    }
}
