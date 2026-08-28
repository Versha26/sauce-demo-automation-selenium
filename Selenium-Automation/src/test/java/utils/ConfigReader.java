package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties;
	
	static {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("Config file not found: " + e.getMessage());
		}
	}
	public static String get(String key) {
		String systemValue = System.getProperty(key);
		if (systemValue != null && !systemValue.isEmpty()) {
			return systemValue;
		}
		return properties.getProperty(key);
	}
}
