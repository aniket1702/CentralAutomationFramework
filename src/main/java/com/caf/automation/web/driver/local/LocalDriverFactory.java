package com.caf.automation.web.driver.local;

import com.caf.automation.web.driver.local.manager.ChromeManager;
import com.caf.automation.web.driver.local.manager.FirefoxManager;
import com.caf.automation.web.enums.Browser;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class LocalDriverFactory {
    private static final Map<Browser, Supplier<WebDriver>> MAP = new EnumMap<>(Browser.class);

    static {
        MAP.put(Browser.CHROME, ChromeManager::getDriver);
        MAP.put(Browser.FIREFOX, FirefoxManager::getDriver);
    }

    private LocalDriverFactory() {
    }

    public static WebDriver getDriver(Browser browserType) {
        return MAP.get(browserType).get();
    }

}
