package com.caf.automation.web.utils;

import com.caf.automation.web.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotProvider {
    private ScreenshotProvider(){}

    public static void getBase64Image()
    {
        byte[] screenshot= ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment("Screenshot","image/png", "png", screenshot);

    }
}
