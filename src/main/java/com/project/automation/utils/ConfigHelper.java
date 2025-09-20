package com.project.automation.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class to handle configuration management and environment-specific settings.
 */
public class ConfigHelper {

    private static final Map<String, String> configProperties;
    private static final String DEFAULT_ENV = "dev";
    private static final String[] VALID_ENVIRONMENTS = {"dev", "qa", "staging", "prod"};

    static {
        configProperties = new HashMap<>();
        loadConfigProperties();
    }

    public static String getEnvironment() {
        String env = System.getProperty("env", DEFAULT_ENV);
        if (!StringUtils.equalsAnyIgnoreCase(env, VALID_ENVIRONMENTS)) {
            throw new RuntimeException("Invalid environment value. Allowed values are dev, qa, staging, prod");
        }
        return env.toLowerCase();
    }

    public static String getDomain() {
        return switch (getEnvironment()) {
            case "dev" -> ".dev.com";
            case "qa" -> ".qa10.com";
            case "staging" -> ".integ.com";
            case "prod" -> "prod.com";
            default -> throw new IllegalStateException("Unexpected value: " + getEnvironment());
        };
    }

    private static void loadConfigProperties() {
        String configFilePath = getConfigFilePath();
        try (java.io.InputStream input = new java.io.FileInputStream(configFilePath)) {
            java.util.Properties prop = new java.util.Properties();
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                configProperties.put(key, prop.getProperty(key));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    private static String getConfigFilePath() {
        if (checkIfConfigInRoot()) {
            return System.getProperty("user.dir") + File.separator + "config.properties";
        } else if (checkIfConfigInResources()) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
                   File.separator + "resources" + File.separator + "config.properties";
        }
        throw new RuntimeException("config.properties file not found in root or resources directory.");
    }

    private static boolean checkIfConfigInRoot() {
        try {
            String rootConfig = System.getProperty("user.dir") + File.separator + "config.properties";
            return new File(rootConfig).exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkIfConfigInResources() {
        try {
            String resourcesConfig = System.getProperty("user.dir") + File.separator + "src" + File.separator +
                                   "test" + File.separator + "resources" + File.separator + "config.properties";
            return new File(resourcesConfig).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getProperty(String key) {
        return configProperties.get(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return configProperties.getOrDefault(key, defaultValue);
    }
}
