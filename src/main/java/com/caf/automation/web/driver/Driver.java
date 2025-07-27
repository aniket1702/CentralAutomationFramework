package com.caf.automation.web.driver;

import com.caf.automation.loggers.LogType;
import com.caf.automation.loggers.LogUtils;
import com.caf.automation.web.constants.CentralAutomationFramework;
import com.caf.automation.web.driver.entity.DriverData;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class Driver {
    private Driver() {
    }

    public static void initDriver() {
        if (Objects.isNull(DriverManager.getDriver())) {
            DriverData driverData = new DriverData(CentralAutomationFramework.BROWSER, CentralAutomationFramework.REMOTE_MODE_TYPE);
            WebDriver driver = DriverFactory
                    .getDriver(CentralAutomationFramework.RUN_MODE_TYPE)
                    .getDriver(driverData);

            LogUtils.log(LogType.PASS, "Driver Initialized");
            DriverManager.setDriver(driver);
            loadURL();
        }
    }

    private static void loadURL() {
        DriverManager.getDriver().get(String.valueOf(CentralAutomationFramework.BASE_URL));
        LogUtils.log(LogType.PASS, "URL Loaded: "+CentralAutomationFramework.BASE_URL);
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unLoad();
            LogUtils.log(LogType.PASS, CentralAutomationFramework.BROWSER + " browser is closed.");
        }

    }

}
