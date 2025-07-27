package com.caf.automation.web.driver.local;

import com.caf.automation.web.driver.IWebDriver;
import com.caf.automation.web.driver.entity.DriverData;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverImpl implements IWebDriver {
    @Override
    public WebDriver getDriver(DriverData driverData) {
        return LocalDriverFactory.getDriver(driverData.getBrowserType());
    }
}
