package com.project.automation.exceptions;

import com.project.automation.data.Constants;

/**
 * placeholder for adding any custom wait exceptions for UI validations
 * eg: InvalidPageLoadException, CustomWaitExceptions, custom webelement exceptions, etc
 * right now it has only one exception class as an example
 */
public class WaitException extends BaseException {

    public WaitException(String message) {
        super(message,"Wait error handling");
    }

    public static class InvalidPageLoadException extends WaitException{

        public InvalidPageLoadException(Throwable cause) {
            super(Constants.UI.INVALID_PAGE_LOAD);
        }
    }


}
