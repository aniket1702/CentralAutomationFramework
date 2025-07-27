package com.caf.automation.web.driver.remote;

import com.caf.automation.web.driver.remote.factory.BrowserStackGridFactory;
import com.caf.automation.web.driver.remote.factory.SeleniumGridFactory;
import com.caf.automation.web.enums.Browser;
import com.caf.automation.web.enums.RemoteMode;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public final class RemoteDriverFactory {
    private static final Map<RemoteMode, Function<Browser, WebDriver>> MAP = new EnumMap<>(RemoteMode.class);

    static {
        MAP.put(RemoteMode.SELENIUM, SeleniumGridFactory::getDriver);
        MAP.put(RemoteMode.BROWSER_STACK, BrowserStackGridFactory::getDriver);
    }

    private RemoteDriverFactory() {
    }

    public static WebDriver getDriver(RemoteMode remoteModeType, Browser browserType) {
        return MAP.get(remoteModeType).apply(browserType);
    }
}
