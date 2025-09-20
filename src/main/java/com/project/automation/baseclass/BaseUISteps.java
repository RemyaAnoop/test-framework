package com.project.automation.baseclass;

import net.serenitybdd.core.steps.UIInteractionSteps;

/**
 * Base UI Steps class to be extended by all specific step definition classes.
 * This class can include common methods and properties shared across different step definitions.
 * Right now it is kept simple, but can be expanded as needed.
 */

public class BaseUISteps extends UIInteractionSteps {

    // This class now properly extends UIInteractionSteps which provides
    // access to pages and WebDriver functionality
}
