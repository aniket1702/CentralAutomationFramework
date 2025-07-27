package com.caf.automation.web.driver.remote.manager.browserstack;

import com.caf.automation.web.constants.CentralAutomationFramework;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public final class BrowserStackChromeManager {
    private BrowserStackChromeManager() {
    }

    public static WebDriver getDriver() {

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", CentralAutomationFramework.BROWSER);

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("buildName", "Build 1.1");
        browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", "4.13.0");

        capabilities.setCapability("bstack:options", browserstackOptions);
        return new RemoteWebDriver(CentralAutomationFramework.BROWSER_STACK_URL, capabilities);
    }
}
