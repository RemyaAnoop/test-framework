package com.project.automation.baseclass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.automation.data.Constants;
import com.project.automation.exceptions.BaseException;
import com.project.automation.utils.ApiUtils;
import com.project.automation.utils.PropertyHelper;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import net.serenitybdd.model.environment.UndefinedEnvironmentVariableException;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseAPI class to handle REST API requests using RestAssured and Serenity.
 * It provides methods to set up the request, including base URI, headers, cookies,
 * query parameters, path parameters, and request body. It also supports GET and POST requests
 * with optional authentication.
 */
public class BaseAPI {
    private String baseUri = null;
    private String basePath = "";
    private final EnvironmentVariables environmentVariables;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> pathParams = new HashMap<>();
    private Object body;
    private String contentType;
    private Response response;
   // private static final Logger  log = Logger.getLogger(BaseAPI.class);


    public BaseAPI(){
        this.environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        RestAssured.config = RestAssuredConfig.config()
            .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
            .httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 10000)
                .setParam("http.socket.timeout", 10000));
        setBaseUri(null); // Initialize with configured URL
    }

    public void setBaseUri(String baseURL) {
        if (baseURL == null || baseURL.isEmpty()) {
            // Always get base URL from serenity.conf when not explicitly provided
            this.baseUri = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("api.base.url");
        } else {
            this.baseUri = baseURL;
        }
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private String toJson(Object o) {
        try { return MAPPER.writeValueAsString(o); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }

// GETTERS && SETTERS

    public void setPathUrl(String path){this.basePath = path;}
    public String getPathUrl(){return basePath;}

    public void setBaseUriFromConfig(String propertyName) throws BaseException {
        try {
            String baseURL = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(propertyName);
            setBaseUri(baseURL);
        } catch (UndefinedEnvironmentVariableException e) {
            throw new BaseException(propertyName + " is not defined in your environment variable. Cannot assign a base URI");
        }
    }

    public String getBaseUri() {
        if (baseUri == null) {
            setBaseUri(null);
        }
        return baseUri;
    }


    public void setBaseURL(String propertyName) throws BaseException{
        try
            {
            setBaseUri(PropertyHelper.getEnvProperty(propertyName));
            }catch (UndefinedEnvironmentVariableException e){
            throw new BaseException(propertyName + " is not defined in your environment variable. Cannot assign a base URI");
        }
    }
    public void setHeader(Map<String, String> headers){
        this.headers = headers;
    }

    public Map<String, String> getHeaders(){return headers;}

    public void setCookie(Map<String, String> cookies){
         this.cookies = cookies;
    }
    public Map<String, String> getCookies(){return this.cookies;}

    public void setQueryParams(Map<String, String> queryParams){
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            this.queryParams.put(entry.getKey(), entry.getValue().toString());
        }
    }
    public Map<String, String> getQueryParams(){return this.queryParams;}

    public void setPathParams(Map<String, String> pathParams){
        this.pathParams = pathParams;
    }
    public Map<String, String> getPathParams(){return this.pathParams;}

    public void setRequestBody(Object body){this.body = toJson(body);
    }
    public Object getRequestBody(){return this.body;}

    public void setContentType(String contentType){this.contentType = contentType;}
    public String getContentType(){return this.contentType;}

    public Response getResponse(){return this.response;}

    private String apiKey;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    // API methods

    private RequestSpecification request = RestAssured.given()
            .relaxedHTTPSValidation()
            .accept("*/*")
            .contentType("application/json");

    public RequestSpecification withAuth() {
        if (apiKey != null && !apiKey.isEmpty()) {
            return request.header("x-apikey", apiKey);
        } else if (getCookies() != null && getCookies().get("token") != null) {
            return request.header("Authorization", "Bearer " + getCookies().get("token"));
        }
        return request;
    }
    public RequestSpecification withoutAuth() {
        return request;
    }

    public void resetRestAssuredObject(){
        RestAssured.reset();
        setBaseUri(null);
        setPathUrl(null);
        response = null;
    }

    private RequestSpecification buildRequest(){
        request.relaxedHTTPSValidation();

        // Ensure we have a base URL
        if (this.baseUri == null || this.baseUri.isEmpty()) {
            setBaseUri(null);
        }

        // Apply authentication
        withAuth();

        // Build the full URL properly
        request.baseUri(this.baseUri);
        if (this.basePath != null && !this.basePath.isEmpty()) {
            request.basePath(this.basePath);
        }

        // Add headers and other configurations
        if (this.headers != null && !headers.isEmpty()) { request.headers(headers); }
        if (this.cookies != null && !cookies.isEmpty()) { request.cookies(cookies); }
        if (this.queryParams != null && !queryParams.isEmpty()) { request.queryParams(queryParams); }
        if (this.pathParams != null && !pathParams.isEmpty()) { request.pathParams(pathParams); }
        if (this.body != null && !body.toString().isEmpty()) { request.body(body); }

        return request;
    }

    // CRUD OPERATIONS //

    public Response getRequest(){
        response = buildRequest()
                .when()
                .get();
        return response;
    }

    public Response getRequestWithoutAuth(){
        response = withoutAuth().spec(buildRequest())
                .when()
                .get();
        return response;
    }

    public Response getRequest(String basePath){
        setBaseURL(basePath);
        response = buildRequest()
                .when()
                .get();
        return response;
    }

    public Response getRequestWithQueryParams(Map<String, String> queryParams){
        setQueryParams(queryParams);
        response = buildRequest()
                .when()
                .get();
        return response;
    }

    public Response postRequest(){
        response = buildRequest()
                .when()
                .post();
        return response;
    }

    public Response postRequest(String requestBody){
        setRequestBody(requestBody);
        response = buildRequest()
                .when()
                .post();
        return response;
    }

    public Response postRequestWithoutAuth(){
        response = withoutAuth().spec(buildRequest())
                .when()
                .post();
        return response;
    }

    public  void retryRequest(){

    }
// can do for different combinations of query param and path param / request body etc.Can also include PUT, DELETE etc.

   public void executeRetries(String httpMethod){
        response = null ;
        RequestSpecification requestSpecification = SerenityRest.given().spec(buildRequest());
        requestSpecification.basePath(this.basePath);
        switch (httpMethod){
            case "GET" -> response = ApiUtils.enableRetries(requestSpecification::get, Constants.API.MAX_RETRIES,Constants.API.MAX_BACKOFF_MS );
            case "POST" -> response = ApiUtils.enableRetries(requestSpecification::post, Constants.API.MAX_RETRIES,Constants.API.MAX_BACKOFF_MS );
        }
   }

   @Override
    public String toString() {
        return String.format("""
                base URI: %s 
                base path: %s
                headers: %s
                query parameters: %s
                path parameters: %s
                body: %s
                response: %s
                """,
                baseUri,
                basePath,
                headers,
                queryParams,
                pathParams,
                body,
                response == null ? null: response.toString());
   }
}
