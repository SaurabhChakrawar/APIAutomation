package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataUtil {

    // Reads a JSON file from testdata folder and returns it as a String
    public static String getTestData(String fileName) {
        try {
            return new String(Files.readAllBytes(
                    Paths.get("src/test/resources/testdata/" + fileName)
            ));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data file: " + fileName, e);
        }
    }
}
