package com.project.automation.baseclass;

import com.project.automation.utils.ApiUtils;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import org.json.JSONObject;

import java.util.Map;

public class BaseApiSteps {
    public BaseAPI baseAPI = new BaseAPI();
    Response response;

    private void storeCookieFromResponse(String cookie) {
        int getCookie = cookie.indexOf(";");
        if(getCookie != -1){
            int lastIndex = cookie.lastIndexOf(";");
            String extractedCookie = cookie.substring(7,getCookie);
            Map<String, String> cookies = baseAPI.getCookies();
            cookies.put(extractedCookie, cookie);
            cookies.put("MAXAGE", cookie.substring(getCookie + 8, lastIndex));
            Serenity.setSessionVariable("cookie Map").to(cookies);
        }
    }

    public Map<String, String> getCookies(){
        return Serenity.sessionVariableCalled("cookie Map");
    }

    @Step("Set Base URL to {0}")
    public void setBaseURL(String baseURL){
        baseAPI.setBaseUri(baseURL);
    }

    @Step("Set Base Path to {0}")
    public void setBasePath(String basePath){
        baseAPI.setPathUrl(basePath);
    }

    @Step("Execute {0} request")
    public Response executeRequest(String httpMethod) {
        switch (httpMethod.toUpperCase()) {
            case "GET" -> response = baseAPI.getRequest();
            case "POST" -> response = baseAPI.postRequest();
            default -> throw new IllegalStateException("Unexpected value: " + httpMethod);
        }
        return response;
    }

    @Step("Execute POST request with body")
    public Response executePostRequest(String requestBody){
        baseAPI.setRequestBody(requestBody);
        response = baseAPI.postRequest();
        return response;
    }

    @Step("Get JSON field value {0}")
    public String getJsonFieldValue(String field) {
        JSONObject jsonResponse = ApiUtils.parse(response.getBody().asString());
        return jsonResponse.get(field).toString();
    }

    @Step("Reset request")
    public void resetRequest(){
        baseAPI.resetRestAssuredObject();
        response = null;
    }

    @Step("Execute request with retries")
    public void tryWithRetry(String httpMethod){
        baseAPI.executeRetries(httpMethod);
    }

    // Response storage and retrieval
    @Step("Get response")
    public Response getCookieResponse(){
        if(response != null && response.getCookies() != null && !response.getCookies().isEmpty()){
            for (Map.Entry<String, String> entry : response.getCookies().entrySet()) {
                storeCookieFromResponse(entry.getKey() + "=" + entry.getValue() + "; Path=/; HttpOnly");
            }
        }
        return response;
    }

    @Step("Parse response body as JSON")
    protected JSONObject getResponseAsJson() {
        return ApiUtils.parse(response.getBody().asString());
    }
}
