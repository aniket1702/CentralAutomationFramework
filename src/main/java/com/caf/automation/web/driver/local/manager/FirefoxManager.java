package com.caf.automation.web.driver.local.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;

import java.util.List;

public final class FirefoxManager {
    private FirefoxManager() {}
    
    public static WebDriver getDriver() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless"));

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("--headless");
        }
        firefoxOptions.addArguments("--remote-allow-origins=*");

        SeleniumManager.getInstance().getBinaryPaths((List<String>) firefoxOptions);
        return new FirefoxDriver(firefoxOptions);
    }
}
