package com.caf.automation.web.driver.remote.manager.selenium;

import com.caf.automation.web.constants.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SeleniumGridFirefoxManager {
    private SeleniumGridFirefoxManager() {
    }

    public static WebDriver getDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        return new RemoteWebDriver(Constant.SELENIUM_GRID_URL, firefoxOptions);
    }
}
