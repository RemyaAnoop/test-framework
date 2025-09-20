package com.project.testautomation.testcases.ui;


import com.project.automation.baseclass.BaseTest;
import com.project.testautomation.steps.ui.UserCreationSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for User Creation related test cases.
 * Contains test methods that utilize the UserCreationSteps for actions and validations.
 * In serenity, we keep test cases separate.In case of cucumber, we keep step definitions separate.
 * Kept basic functionality here. Can be extended as needed.
 */
public class UserCreationTest extends BaseTest {

    // Test methods for user creation will go here

    UserCreationSteps userCreationSteps;

    @Test
    @DisplayName("Happy Path User Creation Test")
    public void userCreationTest() {
        userCreationSteps.createUser("testUser", "test@gmail.com", "Savings");
        Assertions.assertTrue(userCreationSteps.validateMessage("Created ID"));
        System.out.println("User created and validated successfully.");
    }

    @DisplayName("Invalid User Creation Test")
    @Test
    public void invalidUserCreationWithNameErrorTest() {
        String validEmail = "test@gmail.com";
        userCreationSteps.createUser("", validEmail,"Premium");
        Assertions.assertTrue(userCreationSteps.isNameErrorMessageDisplayed("Name is required"));
    }

    @ParameterizedTest(name = "Invalid User Creation Test with email: {0}")
    @DisplayName("Invalid User Creation With Invalid Email Test")
    @ValueSource(strings = {"", "invalidEmail", "user@.com", "user@com", "user.com"})
    public void invalidUserCreationWithEmailErrorTest(String input) {
        String validUsername = "testUser";
        userCreationSteps.createUser(validUsername, input, "Premium");
        Assertions.assertTrue(userCreationSteps.isEmailErrorDisplayed("Email is required or Invalid email format"));
    }

    @ParameterizedTest(name = "[{index}] name=\"{0}\" email=\"{1}\" and type=\"{3}\"")
    @CsvFileSource(resources = "/Data/user.csv", numLinesToSkip = 1)
    @DisplayName("Invalid User Creation With Invalid Email and Name Test")
    @Tag("@Priority-1")
    public void invalidUserCreationWithEmailAndNameErrorTest(String name, String email, String accountType) {
        userCreationSteps.createUser(name, email, accountType);
        if (name.isEmpty()) {
            Assertions.assertEquals(true, userCreationSteps.isNameErrorMessageDisplayed("Name is required"));
        }
        if (email.isEmpty()) {
            Assertions.assertTrue(userCreationSteps.isEmailErrorDisplayed("Email is required"));
        }else if (!email.contains("@") || !email.contains(".")) {
            Assertions.assertTrue(userCreationSteps.isEmailFormatErrorDisplayed("Invalid email format"));
        }
    }
}
