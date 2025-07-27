package com.caf.automation.web.driver.remote;

import com.caf.automation.web.driver.IWebDriver;
import com.caf.automation.web.driver.entity.DriverData;
import org.openqa.selenium.WebDriver;

public class RemoteWebDriverImpl implements IWebDriver {
    @Override
    public WebDriver getDriver(DriverData driverData) {
        return RemoteDriverFactory.getDriver(driverData.getRemoteModeType(), driverData.getBrowserType());
    }
}
