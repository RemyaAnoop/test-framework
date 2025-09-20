package com.project.testautomation.steps.ui;

import com.project.automation.baseclass.BaseUISteps;
import com.project.testautomation.pages.TransactionPage;
import net.serenitybdd.annotations.Step;

/**
 * Step definitions for Transaction Creation related actions.
 * Contains high-level actions that can be performed on the Transaction Page.
 * In serenity, we keep step definitions separate.
 * Kept basic functionality here. Can be extended as needed.
 */
public class TransactionCreationSteps extends BaseUISteps {

    TransactionPage transactionPage;


    @Step("Navigate to Transaction Creation Page")
    public void navigateToTransactionCreationPage() {
           transactionPage.startANewTransactionButton.click();
    }

    @Step("Create transaction with userId: {0}, amount: {1}, type: {2}, recipientId: {3}")
    public void createTransaction(String userId, String amount, String type, String recipientId) {
        transactionPage.userIdInput.type(userId);
        transactionPage.amountInput.type(amount);
        transactionPage.typeSelect.selectByVisibleText(type);
        transactionPage.recipientIdInput.type(recipientId);
        transactionPage.createTxnBtn.click();
    }

    @Step("Get transaction success message")
    public String getTransactionSuccessMessage() {
        transactionPage.txnMsg.waitUntilVisible();
        return transactionPage.txnMsg.getText();
    }

    @Step("Validate negative transaction scenario - {0}")
    public void validateNegativeTransaction(String scenario, String userId, String amount, String type, String recipientId) {
        switch(scenario) {
            case "NEGATIVE_AMOUNT":
                transactionPage.userIdInput.type(userId);
                transactionPage.amountInput.type("-" + amount);
                transactionPage.typeSelect.selectByVisibleText(type);
                transactionPage.recipientIdInput.type(recipientId);
                break;
            case "INVALID_USER":
                transactionPage.userIdInput.type("invalid_" + userId);
                transactionPage.amountInput.type(amount);
                transactionPage.typeSelect.selectByVisibleText(type);
                transactionPage.recipientIdInput.type(recipientId);
                break;
            case "SAME_USER_RECIPIENT":
                transactionPage.userIdInput.type(userId);
                transactionPage.amountInput.type(amount);
                transactionPage.typeSelect.selectByVisibleText(type);
                transactionPage.recipientIdInput.type(userId); // Same as sender
                break;
            case "ZERO_AMOUNT":
                transactionPage.userIdInput.type(userId);
                transactionPage.amountInput.type("0");
                transactionPage.typeSelect.selectByVisibleText(type);
                transactionPage.recipientIdInput.type(recipientId);
                break;
        }
        transactionPage.createTxnBtn.click();
    }

    @Step("Clear transaction form")
    public void clearTransactionForm() {
        transactionPage.userIdInput.clear();
        transactionPage.amountInput.clear();
        transactionPage.recipientIdInput.clear();
    }
}
