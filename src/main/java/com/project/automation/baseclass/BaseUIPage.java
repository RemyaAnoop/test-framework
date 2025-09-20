package com.project.automation.baseclass;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

/**
 * Base UI Page class to be extended by all specific page classes.
 * This class can include common methods and properties shared across different pages.
 * Right now it is kept simple, but can be expanded as needed.
 */
public class BaseUIPage extends PageObject {
    public BaseUIPage(WebDriver driver) {
    }

}
