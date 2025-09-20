package com.project.testautomation.steps.api;

import com.project.automation.api.endpoints.ApiEndpoints;
import com.project.automation.baseclass.BaseApiSteps;
import com.project.automation.api.models.Transaction;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;

import java.math.BigDecimal;

/**
 * Step definitions for account transaction related API interactions.
 * This class uses BaseApiSteps to perform API requests and handle responses.
 * Inclduded basic positive and negative scenarios for transaction creation and retrieval due to time constraints.
 */
public class AccountTransactionSteps extends BaseApiSteps {

    private Transaction transaction;

    public AccountTransactionSteps() {
        this.transaction = new Transaction();
    }

    @Step("Create transaction for userId: {0}, amount: {1}, type: {2}, recipientId: {3}")
    public Response createTransaction(String userId, BigDecimal amount, String type, String recipientId) {
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setRecipientId(recipientId);
        setBasePath(ApiEndpoints.TRANSACTIONS);
        return executePostRequest(transaction.toString());
    }

    @Step("Get transactions for userId: {0}")
    public Response getTransactions(String userId) {
        setBasePath(ApiEndpoints.transactionsByUser(userId));
        return executeRequest("GET");
    }

    @Step("Attempt invalid transaction for userId: {0}, amount: {1}, type: {2}, recipientId: {3}")
    public Response invalidTransaction(String userId, BigDecimal amount, String type, String recipientId) {
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setRecipientId(recipientId);
        setBasePath(ApiEndpoints.INVALIDRESOURCE);
        return executePostRequest(transaction.toString());
    }

    @Step("Reset transaction state")
    public void resetTransactionState() {
        resetRequest();
        transaction = new Transaction();
    }
}
