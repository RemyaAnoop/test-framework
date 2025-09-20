package com.project.automation.api.client;

import com.project.automation.baseclass.BaseAPI;
import com.project.automation.api.endpoints.ApiEndpoints;
import com.project.automation.api.models.Transaction;
import com.project.automation.api.models.User;
import io.restassured.response.Response;

/**
 * ApiClient handles all HTTP requests to the API endpoints.
 * It encapsulates the business logic for API operations.
 */
public class ApiClient {
    private final BaseAPI baseAPI;

    public ApiClient() {
        this.baseAPI = new BaseAPI();
    }

    // User Operations
    public Response createUser(User user) {
        baseAPI.setPathUrl(ApiEndpoints.USERS);
        baseAPI.setRequestBody(user);
        return baseAPI.postRequest();
    }

    public Response getUser(String id) {
        baseAPI.setPathUrl(ApiEndpoints.userById(id));
        return baseAPI.getRequest();
    }

    // Transaction Operations
    public Response createTransaction(Transaction txn) {
        baseAPI.setPathUrl(ApiEndpoints.TRANSACTIONS);
        baseAPI.setRequestBody(txn);
        return baseAPI.postRequest();
    }

    public Response getTransactions(String userId) {
        baseAPI.setPathUrl(ApiEndpoints.transactionsByUser(userId));
        return baseAPI.getRequest();
    }

    // Authentication and Headers
    public void setApiKey(String apiKey) {
        baseAPI.setApiKey(apiKey);
    }

    public void setBearerToken(String token) {
        baseAPI.setHeader(java.util.Map.of("Authorization", "Bearer " + token));
    }

    public void setCustomHeader(String name, String value) {
        baseAPI.setHeader(java.util.Map.of(name, value));
    }

    // Query Parameters
    public void setQueryParams(java.util.Map<String, String> params) {
        baseAPI.setQueryParams(params);
    }

    // Error Testing
    public Response invalidResourceValidation() {
        baseAPI.setPathUrl(ApiEndpoints.INVALIDRESOURCE);
        return baseAPI.getRequest();
    }

    // State Management
    public void reset() {
        baseAPI.resetRestAssuredObject();
    }
}
