package com.project.testautomation.testutils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating random test data, integration with any test data libraries can be added here.
 * Managing Test data factory methods in one place for easy maintenance and reusability.
 */
public class testdataUtils {

    public static String randomEmail() {
        return "user+" + UUID.randomUUID().toString().substring(0,8) + "@test.com";
    }
    public static String randomName() {
        return "TestUser-" + UUID.randomUUID().toString().substring(0,6);
    }

    public static double randomAmount() {
        return Math.round(ThreadLocalRandom.current().nextDouble(1.0, 500.0) * 100.0) / 100.0;
    }

    public enum  accountType {
        PREMIUM,
        TRANSFER,
    }
}
