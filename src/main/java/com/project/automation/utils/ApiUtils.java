package com.project.automation.utils;

import java.util.function.Supplier;
import org.json.JSONObject;

/**
 * Utility class for API related operations.
 * Provides methods for API retries, email validation, and JSON parsing.
 */
public class ApiUtils {

    private ApiUtils(){}

    public static <T> T enableRetries(Supplier<T> supplier, int max_retries, int maxBackoff_ms) {
        for (int i = 0; i <= max_retries; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                if (i == max_retries - 1) {
                    throw new RuntimeException(e);
                }
                long backOff_ms = (long)Math.min(Math.pow(2,i) * 1000, maxBackoff_ms);
                try {
                    Thread.sleep(backOff_ms);
                } catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null;
    }

    public static boolean isEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static JSONObject parse(String body) {
        return new JSONObject(body);
    }
}
