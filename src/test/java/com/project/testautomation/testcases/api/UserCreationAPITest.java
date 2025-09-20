package com.project.testautomation.testcases.api;

import com.project.automation.api.models.User;
import com.project.testautomation.steps.api.UserApiSteps;
import com.project.testautomation.testutils.customAssertions;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for User Creation API.
 * This class includes positive and negative test scenarios for user creation and retrieval.
 * Due to time constraints, only basic scenarios are covered.
 * Kept test cases simple and straightforward. Can be extended as needed for different status codes and edge cases.
 * contract validation can also be added.
 */
public class UserCreationAPITest {

    User user = new User();
    UserApiSteps userCreationSteps = new UserApiSteps();
    customAssertions assertions = new customAssertions();

    @Test
    public void validateUserCreation() {
        user.setName("test_user");
        user.setEmail("test@gmail.com");
        user.setAccountType("premium");

        Response response = userCreationSteps.createUser();
        if(response != null) {
            String createdId = userCreationSteps.fetchCreationId(response);
            assertions.assertUserFields(response, createdId, "test_user", user.getEmail(), "premium");
        } else {
            Assertions.fail("User creation response is null");
        }
    }

    @Test
    public void validateGetUserById() {
        // First create a user, then retrieve it
        Response createResponse = userCreationSteps.createUser();
        if(createResponse != null) {
            String userId = userCreationSteps.fetchCreationId(createResponse);
            Response getResponse = userCreationSteps.getUser(userId);
            assertions.assertUserFields(getResponse, userId, user.getName(), user.getEmail(), user.getAccountType());
        }
    }

    @Test
    public void validateHappyPath() {
        user.setName("happy_path_user");
        user.setEmail("happy@test.com");
        user.setAccountType("standard");

        Response r = userCreationSteps.createUser();
        if(r == null) {
            Assertions.fail("User creation response is null");
        } else {
            System.out.println("User creation response: " + r.asString());
            assertions.assertStatus(r, 201);
            String id = userCreationSteps.fetchCreationId(r);
            Assertions.assertNotNull(id);
        }
    }

    @Test
    public void inValidUserId() {
        Response r = userCreationSteps.getUser("000");
        if(r == null) {
            Assertions.fail("Get user response is null");
        }else {
            System.out.println("Get user response: " + r.asString());
            assertions.assertStatus(r,400);
            assertions.assertValidationMessage(r,"error", "User not found");
        }
    }

    @Test
    public void validateEmptyValues() {
       Response res =  userCreationSteps.createUser_negative("", "", "");
        assertions.assertStatus(res,400);
        assertions.assertValidationMessage(res,"name", "Name is required");
        assertions.assertValidationMessage(res,"email", "Email is required");
        assertions.assertValidationMessage(res,"accountType", "Account type is required");
    }

    @Test
    public  void invalidUserCreation() {
       Response res =  userCreationSteps.createUser_negative("test_user", "badEmail", "premium");
        assertions.assertStatus(res,400);
        assertions.assertValidationMessage(res,"email", "Invalid email format");
    }

    @Test
    public void inValidEndpoint() {
        Response r = userCreationSteps.getFromInvalidUrl();
        if(r != null) {
            System.out.println("Get invalid url response: " + r.asString());
            assertions.assertStatus(r,404);
            assertions.assertValidationMessage(r,"error", "Endpoint Not Found");
        }
    }

}
