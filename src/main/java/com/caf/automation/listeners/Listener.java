package com.caf.automation.listeners;


import com.caf.automation.annotation.AllureTest;
import com.caf.automation.web.constants.Constant;
import com.caf.automation.web.driver.DriverManager;
import com.caf.automation.loggers.LogType;
import com.caf.automation.loggers.LogUtils;
import com.caf.automation.web.utils.InitializeSeleniumServer;
import com.caf.automation.web.utils.ScreenshotProvider;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import org.testng.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


/**
 * <p>The Listener class implements TestNG listeners to manage test and suite execution.</p>
 * It logs various events and integrates with Allure reporting to generate environment details
 * and manage test annotations.
 * <p>
 * This class handles the following events:
 * <ul>
 *     <li>{@link ISuiteListener} - Suite level events</li>
 *     <li>{@link ITestListener} - Test level events</li>
 * </ul>
 * It manages the Allure environment details and provides logging for test events.
 * It performs file copying for Allure report generation upon suite completion.
 *
 * @see ISuiteListener
 * @see ITestListener
 * @see AllureTest
 * @see Constant
 * @see LogUtils
 * @see AllureEnvironmentWriter
 */
public class Listener implements ITestListener, ISuiteListener {

    /**
     * Invoked before the start of a test suite.
     *
     * @param suite The suite under execution
     * @see ISuiteListener#onStart(ISuite)
     */
    @Override
    public void onStart(ISuite suite) {

        if("remote".equalsIgnoreCase(String.valueOf(Constant.RUN_MODE_TYPE)))
        {

                InitializeSeleniumServer.startSeleniumServer();

        }
        LogUtils.log(LogType.PASS, "Test suite started: " + suite.getName());
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", System.getProperty("os.name"))
                        .put("OS Architecture", System.getProperty("os.arch"))
                        .put("Java Version", System.getProperty("java.version"))
                        .put("Java Home", System.getProperty("java.home"))
                        .put("Username", System.getProperty("user.name"))
                        .put("User Directory", System.getProperty("user.dir"))
                        .put("Browser", Constant.BROWSER.toString())
                        .put("Browser.Version", "latest")
                        .put("URL", String.valueOf(Constant.BASE_URL)).build());

    }

    /**
     * Invoked after the execution of a test suite.
     *
     * @param suite The suite that has finished execution
     * @see ISuiteListener#onFinish(ISuite)
     */
    @Override
    public void onFinish(ISuite suite) {
        LogUtils.log(LogType.PASS, "Test suite finished: " + suite.getName());
        LogUtils.log(LogType.PASS, suite.getResults().size() + " tests passed in the suite.");
        InitializeSeleniumServer.stopServer();


    }

    /**
     * Copies historical data to the Allure results directory for reporting purposes.
     */
    private void copyHistoryToAllureResults() {
        Path source = Paths.get("target/allure-report/history");
        Path destination = Paths.get("target/allure-results/history");

        try {
            Files.createDirectories(destination);
            try (Stream<Path> files = Files.walk(source))
            {
                files.filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                Files.copy(file, destination.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    /**
     * Invoked before the execution of a test method.
     *
     * @param result Information about the test method to be executed
     * @see ITestListener#onTestStart(ITestResult)
     */
    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.log(LogType.PASS, "Test started: " + result.getMethod().getMethodName());

            AllureTest allureTest = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(AllureTest.class);
            if (allureTest != null) {
                Allure.story(allureTest.story());
                Allure.description(allureTest.description());
                Allure.label("owner", allureTest.owner());
                Allure.label("severity", allureTest.severity().toString());
            }



    }


    /**
     * Invoked after a test method passes successfully.
     *
     * @param result Information about the passed test method
     * @see ITestListener#onTestSuccess(ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.log(LogType.PASS, "Test passed: " + result.getName());
    }


    /**
     * Invoked after a test method fails during execution.
     *
     * @param result Information about the failed test method
     * @see ITestListener#onTestFailure(ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult result) {
        if(DriverManager.getDriver()!=null)
        {
            ScreenshotProvider.getBase64Image();
        }
        LogUtils.log(LogType.FAIL, "Test failed: " + result.getName());
    }

    /**
     * Invoked when a test method is skipped.
     *
     * @param result Information about the skipped test method
     * @see ITestListener#onTestSkipped(ITestResult)
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.log(LogType.SKIP, "Test skipped: " + result.getName());
    }


    /**
     * Invoked when a test method fails but is within the defined success percentage.
     *
     * @param result Information about the test method that failed within success percentage
     * @see ITestListener#onTestFailedButWithinSuccessPercentage(ITestResult)
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getName();
        int passedTestsCount = result.getTestContext().getPassedTests().size();
        int failedTestsCount = result.getTestContext().getFailedTests().size();

        int totalTestsCount = passedTestsCount + failedTestsCount;
        float successPercentage = (float) passedTestsCount / totalTestsCount * 100;
        // Log that the test failed but within the success percentage
        LogUtils.log(LogType.FAIL, "Test '" + testName + "' failed within the success percentage.");
        LogUtils.log(LogType.PASS, "Success Percentage: " + successPercentage + "%");

    }


    /**
     * Invoked before running all the test methods belonging to the classes inside
     * the &lt;test&gt; tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    @Override
    public void onStart(ITestContext context) {
        LogUtils.log(LogType.PASS, "Test context started: " + context.getName());
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the
     * &lt;test&gt; tag have run and all their Configuration methods have been
     * called.
     *
     * @param context The test context
     */
    @Override
    public void onFinish(ITestContext context) {
        LogUtils.log(LogType.PASS, "Test context finished: " + context.getName());
        copyHistoryToAllureResults();
    }
}
