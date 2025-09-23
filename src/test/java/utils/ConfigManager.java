package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    // Load properties once based on env
    static {
        String env = System.getProperty("env", "dev"); // default is dev
        String path = "src/test/resources/config/config-" + env + ".properties";
        System.out.println("Loaded config for env: " + env + " from " + path);
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
            System.out.println("Loaded config for env: " + env + " from " + path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config for env: " + env, e);
        }
    }

    // Fetch property
    public static String getProperty(String key) {
    	System.out.println("key-----------"+key);
        return properties.getProperty(key);
    }

    // Optional: Override property at runtime
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
