package com.project.testautomation.testutils;

import com.project.automation.utils.ApiUtils;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Custom assertion methods for API and UI tests can be  added here.
 * This class provides reusable assertions to validate API responses and UI elements.
 * Example assertions include status code checks, response body validations, and JSON field verifications.
 * These methods help maintain clean and readable test code by encapsulating common validation logic.
 */
public class customAssertions {


    public  void assertStatus(Response response,int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    public void assertBody(Response response, Matcher<?> matcher) {
        MatcherAssert.assertThat(response.getBody().asString(), (Matcher<String>) matcher);
    }

    /**
     * Parse response body as JSONObject for JSON validation and manipulation
     * @param response API response
     * @return JSONObject representation of response body
     */
    public JSONObject getResponseAsJson(Response response) {
        return ApiUtils.parse(response.getBody().asString());
    }
    /**
     * Validate specific JSON field in response
     * @param response API response
     * @param field JSON field to validate
     * @param expectedValue expected value of the field
     */
    public void assertJsonField(Response response, String field, Object expectedValue) {
        org.json.JSONObject jsonResponse = getResponseAsJson(response);
        MatcherAssert.assertThat(String.format("Field '%s' value matches", field),
                jsonResponse.get(field).toString(), org.hamcrest.Matchers.equalTo(expectedValue.toString()));
    }

    @Step("Assert user fields")
    public void assertUserFields(Response res, String id, String name, String email, String accountType) {
        assertThat(res.jsonPath().getString("id"), equalTo(id));
        assertThat(res.jsonPath().getString("name"), equalTo(name));
        assertThat(res.jsonPath().getString("email"), equalTo(email));
        assertThat(res.jsonPath().getString("accountType"), equalTo(accountType));
    }

    @Step("Assert response JSON path {0} equals {1}")
    public void assertValidationMessage(Response response,String jsonPath, String expected) {
        assertThat(response.jsonPath().getString(jsonPath), equalTo(expected));
    }

    @Step("Assert email is valid format {0}")
    public void assertEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        assertThat("Invalid email format: " + email, email, org.hamcrest.Matchers.matchesPattern(emailRegex));
    }
}
