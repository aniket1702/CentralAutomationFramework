package com.caf.automation.web.driver.remote.factory;

import com.caf.automation.web.driver.remote.manager.selenium.SeleniumGridChromeManager;
import com.caf.automation.web.driver.remote.manager.selenium.SeleniumGridFirefoxManager;
import com.caf.automation.web.enums.Browser;
import org.openqa.selenium.WebDriver;

public final class SeleniumGridFactory {
    private SeleniumGridFactory() {
    }

    public static WebDriver getDriver(Browser browserType) {
        return browserType == Browser.CHROME
                ? SeleniumGridChromeManager.getDriver()
                : SeleniumGridFirefoxManager.getDriver();
    }
}
