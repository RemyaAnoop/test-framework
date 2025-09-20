package com.project.automation.data;

/**
 * This class contains all the constant values used across the project.
 * It is a good practice to keep such values in a single place for easy management and to avoid hardcoding them throughout the codebase.
 */
public final class Constants {

    /**
     * Any non changing String can go here.
     * eg: Base Url ,status codes, dir path etc.
     */

    private Constants() {throw new IllegalStateException("Constants");}

    //Urls

    public static final class API {
        public static final String DEFAULT_BASE_URL = "http://localhost:8080/";
        public static final int MAX_RETRIES = 3;
        public static final int MAX_BACKOFF_MS = 1000;

        //status codes
        public static final String BAD_REQUEST = "Bad Request";
        public static final String UNAUTHORIZED = "Unauthorized";
        public static final String FORBIDDEN = "forbidden";
        public static final String NOT_FOUND = "Not Found";
        public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    }

    public static final class UI {
        public static final String INVALID_PAGE_LOAD = "Not the right page loaded";
    }

}
