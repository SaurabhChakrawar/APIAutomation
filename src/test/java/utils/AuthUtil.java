package utils;

import java.util.HashMap;
import java.util.Map;

public class AuthUtil {

    private static String bearerToken = null;
    private static String refreshToken = null;
    private static long tokenExpiryTime = 0;

    //private static final String API_KEY = "reqres-free-v1"; // Replace with config/env
   // private static final String CLIENT_ID = "your-client-id";   // For OAuth2
    //private static final String CLIENT_SECRET = "your-client-secret";
   // private static final String OAUTH_URL = "https://api.example.com/oauth/token";

    // ----------------------------
    // 1. API Key Authentication
    // ----------------------------
    public static Map<String, String> getApiKeyHeaders() {
        String apiKey = ConfigManager.getProperty("apiKey");
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key", apiKey);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    // ----------------------------
    // 2. Bearer Token Authentication
    // ----------------------------
    public static Map<String, String> getBearerHeaders() {
        ensureValidBearerToken();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    // ----------------------------
    // 3. OAuth2 Authentication
    // ----------------------------
    public static Map<String, String> getOAuth2Headers() {
        ensureValidBearerToken(); // Reuse same token logic
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    // ----------------------------
    // Internal Token Handling
    // ----------------------------
    private static void ensureValidBearerToken() {
        if (bearerToken == null || System.currentTimeMillis() >= tokenExpiryTime) {
            fetchNewToken();
        }
    }

    private static void fetchNewToken() {
        // 🔹 Dummy implementation
        // In real project, make HTTP POST to OAUTH_URL with CLIENT_ID + CLIENT_SECRET
        // Example: using RestAssured or HttpClient
        
        bearerToken = "dummy-jwt-token-" + System.currentTimeMillis();
        refreshToken = "dummy-refresh-token-" + System.currentTimeMillis();
        tokenExpiryTime = System.currentTimeMillis() + (60 * 60 * 1000); // valid 1h
    }

    public static void refreshTokenIfUnauthorized(int statusCode) {
        if (statusCode == 401) {
            System.out.println("Received 401 Unauthorized → Refreshing token...");
            fetchNewToken();
        }
    }

    // ----------------------------
    // Utility
    // ----------------------------
    public static void clearTokens() {
        bearerToken = null;
        refreshToken = null;
        tokenExpiryTime = 0;
    }
}
