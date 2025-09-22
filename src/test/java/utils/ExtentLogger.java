package utils;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentLogger {

    private ExtentLogger() {
        // private constructor to prevent instantiation
    }

    // 🔹 General info log
    public static void info(String message) {
        ExtentTestListener.getTest().info(message);
    }

    // 🔹 Request log
    public static void request(String message) {
        if (isJson(message)) {
            ExtentTestListener.getTest().info("📤 Request Body:")
                    .info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
        } else {
            ExtentTestListener.getTest().info("📤 Request:\n" + message);
        }
    }

    // 🔹 Response log
    public static void response(String message) {
        if (isJson(message)) {
            ExtentTestListener.getTest().info("📥 Response Body:")
                    .info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
        } else {
            ExtentTestListener.getTest().info("📥 Response:\n" + message);
        }
    }

    // 🔹 Validation log (✅ / ❌)
    public static void validation(String message, boolean status) {
        if (status) {
            ExtentTestListener.getTest().pass("✅ " + message);
        } else {
            ExtentTestListener.getTest().fail("❌ " + message);
        }
    }

    // 🔹 Utility: check if string looks like JSON
    private static boolean isJson(String str) {
        if (str == null) return false;
        str = str.trim();
        return (str.startsWith("{") && str.endsWith("}")) ||
               (str.startsWith("[") && str.endsWith("]"));
    }
}
