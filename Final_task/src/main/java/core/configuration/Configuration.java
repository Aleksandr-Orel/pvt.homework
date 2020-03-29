package core.configuration;

import core.browser.DriverType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\java\\resources\\";
    private static Properties properties;

    public static void readProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(RESOURCES_PATH, "browser.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "url.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "dbRequest.properties")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static DriverType getDriverType() {
        if (properties == null) {
            readProperties();
        }
        return DriverType.valueOf(properties.getProperty("browserType"));
    }

    public static String getMainApplicationPageUrl() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("mainApplicationPage");
    }

    public static String  getInboxPageUrl() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("inboxPage");
    }

    public static String getUnreadMessagesPageUrl() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("unreadMessagesPage");
    }

    public static String getMessagesWithFlagPageUrl() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("messagesWithFlagPage");
    }

    public static String getRightLoginAndPassword() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("rightLoginAndPassword");
    }

    public static String getRightLoginWrongPassword() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("rightLoginWrongPassword");
    }

    public static String getWrongLoginRightPassword() {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty("wrongLoginRightPassword");
    }
}
