package com.project.automation.utils;

import net.serenitybdd.model.environment.ConfiguredEnvironment;

/**
 * Utility class to handle both system environment variables and Serenity properties.
 * Can add multiple methods to fetch different types of properties as needed.
 */
public class PropertyHelper {

    public static String getEnvProperty(String key) {
        String value = System.getenv(key);
        if (value == null) {
            value = System.getProperty(key);
        }
        return value;
    }

    public static String getEnvProperty(String key, String defaultValue) {
        String value = getEnvProperty(key);
        return value != null ? value : defaultValue;
    }

    public static String getProperty(String key) {
        return ConfiguredEnvironment.getEnvironmentVariables().getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }
}
