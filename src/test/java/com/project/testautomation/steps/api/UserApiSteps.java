package com.project.testautomation.steps.api;

import com.project.automation.api.client.ApiClient;
import com.project.automation.api.models.User;
import com.project.automation.baseclass.BaseApiSteps;
import com.project.automation.utils.ApiUtils;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;

public class UserApiSteps extends BaseApiSteps {

    private final User user = new User();
    private final ApiClient apiClient = new ApiClient();

    @Step("Create user")
    public Response createUser() {
        return apiClient.createUser(user);
    }

    @Step("Get user by id {0}")
    public Response getUser(String id) {
        return apiClient.getUser(id);
    }

    @Step("Create user negative scenario - name: {0}, email: {1}, accountType: {2}")
    public Response createUser_negative(String name, String badEmail, String accountType) {
        User invalidUser = new User();
        invalidUser.setName(name);
        invalidUser.setEmail(badEmail);
        invalidUser.setAccountType(accountType);
        return apiClient.createUser(invalidUser);
    }

    @Step("Get user email from response")
    public String fetchUserEmail(Response response) {
        String email = response.jsonPath().getString("email");
        if (!ApiUtils.isEmail(email)) {
            throw new IllegalStateException("Response contains invalid email format");
        }
        return email;
    }

    @Step("Get user id from response")
    public String fetchCreationId(Response response) {
        return response.jsonPath().getString("id");
    }

    @Step("Get from invalid api url")
    public Response getFromInvalidUrl() {
        return apiClient.invalidResourceValidation();
    }

    @Step("Reset API state")
    public void resetApiState() {
        resetRequest();
    }

    public void setUserDetails(String name, String email, String accountType) {
        user.setName(name);
        user.setEmail(email);
        user.setAccountType(accountType);
    }
}

