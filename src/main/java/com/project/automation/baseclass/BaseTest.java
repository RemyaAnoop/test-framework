package com.project.automation.baseclass;

import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

/**
 * Base test class to be extended by all UI test classes.
 * This class manages the WebDriver instance and can include common setup or teardown methods for test execution.
 * Right now I kept it simple, but can expand it as needed.
 */
public class BaseTest {

    @Managed
    WebDriver driver;

    public BaseTest(){
        //can add logging or reporting setup here
    }

    /**
     * Get the managed WebDriver instance
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
