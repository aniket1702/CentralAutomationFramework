package com.caf.automation.web.driver;

import com.caf.automation.web.driver.entity.DriverData;
import org.openqa.selenium.WebDriver;

public interface IWebDriver {
    WebDriver getDriver(DriverData driverData);


}
