package com.project.testautomation.testcases.ui;

import com.project.automation.baseclass.BaseTest;
import com.project.testautomation.steps.ui.TransactionCreationSteps;
import com.project.testautomation.pages.TransactionPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for Transaction Creation related test cases.
 * Contains test methods that utilize the TransactionCreationSteps for actions and validations.
 * In serenity, we keep test cases separate. In case of cucumber, we keep step definitions separate.
 * Kept basic functionality here. Can be extended as needed.
 */
public class TransactionCreationTest extends BaseTest {

    TransactionCreationSteps transactionSteps;

    @Test
    @DisplayName("Happy Path Transaction Creation Test")
    public void createTransactionForm() {
        transactionSteps.navigateToTransactionCreationPage();
        transactionSteps.createTransaction("123", "100", "payment", "456");
        String successMessage = transactionSteps.getTransactionSuccessMessage();
        Assertions.assertTrue(successMessage.contains("Transaction created successfully"));
    }

    @Test
    @DisplayName("Validate transaction form with invalid data")
    public void validateTransactionForm() {
        transactionSteps.navigateToTransactionCreationPage();
        transactionSteps.validateNegativeTransaction("SAME_USER_RECIPIENT", "23", "100", "payment", "23");
        String errorMessage = transactionSteps.getTransactionSuccessMessage();
        Assertions.assertTrue(errorMessage.contains("Error"));
        transactionSteps.clearTransactionForm();
    }

    @Test
    @DisplayName("Validate negative transaction scenarios")
    public void validateNegativeTransactionScenarios() {
        transactionSteps.navigateToTransactionCreationPage();

        // Test negative amount scenario
        transactionSteps.validateNegativeTransaction("NEGATIVE_AMOUNT", "123", "100", "payment", "456");
        String negativeAmountError = transactionSteps.getTransactionSuccessMessage();
        Assertions.assertTrue(negativeAmountError.contains("Error"));

        // Clear form for next test
        transactionSteps.clearTransactionForm();
    }

    @Test
    @DisplayName("Validate invalid user transaction scenario")
    public void validateInvalidUserTransactionScenario() {
        transactionSteps.navigateToTransactionCreationPage();

        // Test invalid user scenario
        transactionSteps.validateNegativeTransaction("INVALID_USER", "invalid_123", "100", "payment", "456");
        String invalidUserError = transactionSteps.getTransactionSuccessMessage();
        Assertions.assertTrue(invalidUserError.contains("Error"));
    }
}
