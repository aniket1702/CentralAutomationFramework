package com.caf.automation.web.driver.local.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeManager {
    private ChromeManager() {
    }

    public static WebDriver getDriver() {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        ChromeOptions chromeOptions = new ChromeOptions();

        if (headless) {
            chromeOptions.addArguments("--headless=new"); // Better headless support
        }

        chromeOptions.addArguments("--remote-allow-origins=*");

        // No need for SeleniumManager.getInstance().getBinaryPaths(...)
        // ChromeDriver will auto-detect binaries using Selenium Manager (Selenium 4.6+)

        return new ChromeDriver(chromeOptions);

    }
}
