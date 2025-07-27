package com.caf.automation.web.driver.remote.manager.browserstack;

import com.caf.automation.web.constants.CentralAutomationFramework;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public final class BrowserStackFirefoxManager {
    private BrowserStackFirefoxManager() {
    }

    public static WebDriver getDriver() {

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Firefox");
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("buildName", "Firefox 1.2");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "4.13.0");
        capabilities.setCapability("bstack:options", browserstackOptions);

        return new RemoteWebDriver(CentralAutomationFramework.BROWSER_STACK_URL, capabilities);
    }
}
