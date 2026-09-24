package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("configuration.properties could not be read: " + e.getMessage(), e);
        }
    }

    /** A -D system property wins over configuration.properties. Returns null when neither is set. */
    public static String getProperty(String key) {
        String override = System.getProperty(key);
        return override != null ? override : properties.getProperty(key);
    }

    public static String getRequiredProperty(String key) {
        String value = getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing setting '" + key + "'. Copy configuration.properties.example to "
                    + "configuration.properties and fill it in, or pass -D" + key + "=<value>.");
        }
        return value;
    }
}
