package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            if (input == null) {
                throw new RuntimeException("configuration.properties was not found on the classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("configuration.properties could not be read: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        String override = System.getProperty(key);
        return override != null ? override : properties.getProperty(key);
    }
}
