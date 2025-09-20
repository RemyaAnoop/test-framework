package com.project.testautomation.testcases.api;

import com.project.automation.api.models.Transaction;
import com.project.testautomation.steps.api.AccountTransactionSteps;
import com.project.testautomation.testutils.customAssertions;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for Account Transaction API.
 * This class includes positive and negative test scenarios for transaction creation and retrieval.
 * Due to time constraints, only basic scenarios are covered.
 * Kept test cases simple and straightforward. Can be extended as needed for different status codes and edge cases.
 * Contract validation can also be added.
 */
public class TransactionAPITests {

    AccountTransactionSteps accntTxn = new AccountTransactionSteps();
    Transaction txn = new Transaction();
    customAssertions assertions = new customAssertions();

    @Test
    public void validateCreateTransaction() {
        txn.setUserId("123");
        txn.setAmount(new java.math.BigDecimal("250.75"));
        txn.setType("transfer");
        txn.setRecipientId("456");

        Response response = accntTxn.createTransaction(txn.getUserId(), txn.getAmount(), txn.getType(), txn.getRecipientId());
        assertions.assertStatus(response, 201);
        assertions.assertJsonField(response, "userId", txn.getUserId());
        assertions.assertJsonField(response, "amount", txn.getAmount().toString());
        assertions.assertJsonField(response, "type", txn.getType());
        assertions.assertJsonField(response, "recipientId", txn.getRecipientId());
    }

    @Test
    public void validateResponseBody() {
        Response response = accntTxn.createTransaction("123", new java.math.BigDecimal("250.75"), "transfer", "456");
        assertions.assertStatus(response, 201);
        assertions.assertBody(response, org.hamcrest.Matchers.containsString("transactionId"));
    }

    @Test
    public void validateGetTransactions() {
        Response response = accntTxn.getTransactions("123");
        assertions.assertStatus(response, 200);
    }

    @Test
    public void validateGetTransactionsByUserId() {
        Response response = accntTxn.getTransactions("123");
        assertions.assertStatus(response, 400);
        assertions.assertJsonField(response, "error", "No transactions found for userId: 123");
    }

    @Test
    public void validateTransactionsNegative() {
        Response response = accntTxn.invalidTransaction("123", new java.math.BigDecimal("250.75"), "transfer", "456");
        assertions.assertStatus(response, 401);
        assertions.assertJsonField(response, "error", "Unauthorized access");
    }

    @AfterEach
    public void cleanup() {
        accntTxn.resetTransactionState();
    }
}
