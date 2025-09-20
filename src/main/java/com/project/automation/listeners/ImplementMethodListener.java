package com.project.automation.listeners;

import net.serenitybdd.core.listeners.AbstractStepListener;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.domain.TestResult;
import net.thucydides.model.screenshots.ScreenshotAndHtmlSource;
import net.thucydides.model.steps.StepFailure;

import java.time.ZonedDateTime;
import java.util.List;

/**
 *  * ImplementMethodListener is a custom listener that extends AbstractStepListener.
 * Since I am using Serenity structure, I need to implement all the methods of AbstractStepListener.
 * However, I don't need to use all of them, so I am providing empty implementations.
 * This way, I can avoid implementing unnecessary methods in my custom listener.
 *
 * This is required if we want to push the results to any other test management tool. such as TestRail, qTest etc.
 */

public class ImplementMethodListener  extends AbstractStepListener {
    @Override
    public void testStarted(String description, String id) {

    }

    @Override
    public void testStarted(String description, String id, ZonedDateTime startTime) {

    }

    @Override
    public void testFinished(TestOutcome result, boolean isInDataDrivenTest, ZonedDateTime finishTime) {

    }

    @Override
    public void stepFailed(StepFailure failure, List<ScreenshotAndHtmlSource> screenshotList, boolean isInDataDrivenTest, ZonedDateTime timestamp) {

    }

    @Override
    public void stepFinished(List<ScreenshotAndHtmlSource> screenshotList, ZonedDateTime time) {

    }

    @Override
    public void testRunFinished() {

    }

    @Override
    public void takeScreenshots(List<ScreenshotAndHtmlSource> screenshots) {

    }

    @Override
    public void takeScreenshots(TestResult testResult, List<ScreenshotAndHtmlSource> screenshots) {

    }
}
