package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static Properties properties = new Properties();
	private static final String CONFIG_FILE = "src/test/resources/config/config-dev.properties";

	static {

		try {
			FileInputStream fis = new FileInputStream(CONFIG_FILE);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		String env = System.getProperty("env", "dev"); // default dev
		String path = "src/test/resources/config/config-" + env + ".properties";
		try (FileInputStream input = new FileInputStream(path)) {
			Properties props = new Properties();
			props.load(input);
			return props.getProperty(key);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config for env: " + env, e);
		}
	}

	public static void setProperty(String key, String value) {
		properties.setProperty(key, value);
		try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
			properties.store(fos, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
