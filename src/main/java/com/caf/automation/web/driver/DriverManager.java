package com.caf.automation.web.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();
    
    private DriverManager() {}
    
    public static WebDriver getDriver() {
        return THREAD_LOCAL.get();
    }
    
    public static void setDriver(WebDriver driver) {
        THREAD_LOCAL.set(driver);
    }
    
    public static void unLoad() {
        THREAD_LOCAL.remove();
    }
}
