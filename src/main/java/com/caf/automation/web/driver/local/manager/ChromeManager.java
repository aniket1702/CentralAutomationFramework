package com.caf.automation.web.driver.local.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;

import java.util.List;

public final class ChromeManager {
    private ChromeManager() {
    }

    public static WebDriver getDriver() {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless"));

        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments("--remote-allow-origins=*");

        SeleniumManager.getInstance().getBinaryPaths((List<String>) chromeOptions);
        return new ChromeDriver(chromeOptions);

    }
}
