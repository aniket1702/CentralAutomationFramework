package com.caf.automation.web.driver.remote.manager.selenium;

import com.caf.automation.web.constants.Constant;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SeleniumGridChromeManager {
    private SeleniumGridChromeManager() {
    }

    public static RemoteWebDriver getDriver() {

        ChromeOptions chromeOptions = new ChromeOptions();
        
        return new RemoteWebDriver(Constant.SELENIUM_GRID_URL, chromeOptions);
    }
}
