package com.caf.automation.web.driver;

import com.caf.automation.web.constants.Constant;
import com.caf.automation.web.driver.entity.DriverData;
import com.caf.automation.web.enums.loggers.LogType;
import com.caf.automation.web.loggers.LogUtils;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class Driver {
    private Driver() {
    }

    public static void initDriver() {
        if (Objects.isNull(DriverManager.getDriver())) {
            DriverData driverData = new DriverData(Constant.BROWSER, Constant.REMOTE_MODE_TYPE);
            WebDriver driver = DriverFactory
                    .getDriver(Constant.RUN_MODE_TYPE)
                    .getDriver(driverData);

            LogUtils.log(LogType.PASS, "Driver Initialized");
            DriverManager.setDriver(driver);
            loadURL();
        }
    }

    private static void loadURL() {
        DriverManager.getDriver().get(String.valueOf(Constant.BASE_URL));
        LogUtils.log(LogType.PASS, "URL Loaded: "+Constant.BASE_URL);
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unLoad();
            LogUtils.log(LogType.PASS, Constant.BROWSER + " browser is closed.");
        }

    }

}
