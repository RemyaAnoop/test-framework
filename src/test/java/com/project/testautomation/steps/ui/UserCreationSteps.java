package com.project.testautomation.steps.ui;

import com.project.automation.baseclass.BaseUISteps;
import com.project.automation.utils.ApiUtils;
import com.project.testautomation.pages.UserPage;
import net.serenitybdd.annotations.Step;

/**
 * Step definitions for User Creation related actions.
 * Contains high-level actions that can be performed on the User Page.
 * In serenity, we keep step definitions separate.
 * Kept basic functionality here. Can be extended as needed.
 */
public class UserCreationSteps extends BaseUISteps {

    UserPage userPage;

    public UserCreationSteps(UserPage userPage) {
        this.userPage = userPage;
    }

    @Step("Navigate to User Creation Page")
    public void loadHomePage() {
        userPage.navigateToUserPage();
    }

    public void fillUserCreationFields(String name, String email, String accountType) {
        if (!ApiUtils.isEmail(email)) {
            throw new IllegalArgumentException("Invalid email format provided to UI form: " + email);
        }
        userPage.nameInput.type(name);
        userPage.emailInput.type(email);
        userPage.accountTypeSelect.selectByVisibleText(accountType);
        userPage.createUserBtn.click();
    }

    public String getSuccessMessage() {
        userPage.userMsg.waitUntilVisible();
        return userPage.userMsg.getText();
    }

    @Step("Create user with name: {0}, email: {1}, account type: {2}")
    public String createUser(String name, String email, String accountType) {
        loadHomePage();
        fillUserCreationFields(name, email, accountType);
        return getSuccessMessage();
    }

    @Step("validate the message: {0}")
    public boolean validateMessage(String expectedMessage) {
        return getSuccessMessage().contains(expectedMessage);
    }

    public String getNameError() {
        return userPage.nameError.isVisible() ? userPage.nameError.getText() : "";
    }

    public String getEmailError() {
        return userPage.emailError.isVisible() ? userPage.emailError.getText() : "";
    }

    @Step("Check that name error is displayed")
    public boolean isNameErrorMessageDisplayed(String expectedMessage) {
        return getNameError().contains(expectedMessage);
    }

    @Step("Check that email error is displayed")
    public boolean isEmailErrorDisplayed(String expectedMessage) {
        return getEmailError().contains(expectedMessage);
    }

    @Step("Check that email format error is shown")
    public boolean isEmailFormatErrorDisplayed(String expectedError) {
        return userPage.emailError.isVisible() && userPage.emailError.getText().contains(expectedError);
    }
}
