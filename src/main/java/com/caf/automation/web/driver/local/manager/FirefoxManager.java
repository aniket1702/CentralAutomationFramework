package com.caf.automation.web.driver.local.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxManager {
    private FirefoxManager() {}
    
    public static WebDriver getDriver() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (headless) {
            firefoxOptions.addArguments("--headless=new"); // More stable in newer Firefox
        }

        firefoxOptions.addArguments("--remote-allow-origins=*");

        // Selenium Manager auto-handles driver binaries
        return new FirefoxDriver(firefoxOptions);
    }
}
