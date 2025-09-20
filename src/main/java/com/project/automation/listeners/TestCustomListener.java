package com.project.automation.listeners;


import net.thucydides.model.domain.TestOutcome;

import java.time.ZonedDateTime;

/**
 * TestCustomListener is a custom listener that extends ImplementMethodListener.
 * This class overrides the testFinished method to provide custom behavior when a test finishes.
 * You can implement logic to post the test result to an external system or perform other actions as needed.
 * if I want to push the results to any other test management tool. such as TestRail, qTest etc. after each test execution,
 * I need to override the testFinished method.
 * This enables me to hook into the test lifecycle and execute custom code when a test completes.
 * Works Similar to TestNG Listeners or ITestListener.
 */
public class TestCustomListener extends ImplementMethodListener {

    @Override
    public void testFinished(TestOutcome outcome, boolean isInDatadrivenTest, ZonedDateTime time){
        //implement listener to post the result somewhere.
    }

}
