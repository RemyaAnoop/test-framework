package com.project.automation.api.endpoints;

/***
 * This class contains all the API endpoints used in the tests.
 * It provides a centralized location for managing and updating API paths.
 * Each endpoint is defined as a constant string. Dynamic endpoints can be created using static methods that accept parameters.
 * This approach enhances maintainability and readability of the test code.
 **/
public class ApiEndpoints {
    public static final String USERS = "/api/users";
    public static String userById(String id) { return USERS + "/" + id; }
    public static final String TRANSACTIONS = "/api/transactions";
    public static String transactionsByUser(String userId) { return TRANSACTIONS + "/" + userId; }
    public static final String INVALIDRESOURCE = "/api/invalid-resource";

}
