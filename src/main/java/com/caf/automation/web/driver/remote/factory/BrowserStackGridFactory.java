package com.caf.automation.web.driver.remote.factory;

import com.caf.automation.web.driver.remote.manager.browserstack.BrowserStackChromeManager;
import com.caf.automation.web.driver.remote.manager.browserstack.BrowserStackFirefoxManager;
import com.caf.automation.web.enums.Browser;
import org.openqa.selenium.WebDriver;

public final class BrowserStackGridFactory {
    private BrowserStackGridFactory() {
    }

    public static WebDriver getDriver(Browser browserType) {

        return browserType == Browser.CHROME
                ? BrowserStackChromeManager.getDriver()
                : BrowserStackFirefoxManager.getDriver();
    }
}
