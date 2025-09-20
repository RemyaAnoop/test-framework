package com.project.automation.exceptions;

import com.project.automation.data.Constants;

/**
 * This class will contain all the custom exception class that can be expected for the API call.
 * eg: BadRequestException
 * UnAuthorizedException
 * ForbiddenException
 * CustomExceptions
 */
public class ApiException  extends BaseException {

    public ApiException(String message) {super(message, "API Exception");}

    public ApiException(String message, Throwable cause) {super(message,"API Exception",cause);}

    public static class BadRequestException extends ApiException {

        public BadRequestException(Throwable cause) {
            super(Constants.API.BAD_REQUEST, cause);
        }

        public BadRequestException(){
            super(Constants.API.BAD_REQUEST);
        }
    }

    public static class UnAuthorizedException extends ApiException {

        public UnAuthorizedException(Throwable cause) {
            super(Constants.API.UNAUTHORIZED, cause);
        }

        public UnAuthorizedException(){
            super(Constants.API.UNAUTHORIZED);
        }
    }
}