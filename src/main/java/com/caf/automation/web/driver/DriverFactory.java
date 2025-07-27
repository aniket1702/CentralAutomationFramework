package com.caf.automation.web.driver;

import com.caf.automation.web.driver.local.LocalWebDriverImpl;
import com.caf.automation.web.driver.remote.RemoteWebDriverImpl;
import com.caf.automation.web.enums.RunMode;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {
    private DriverFactory() {
    }

    private static final Map<RunMode, Supplier<IWebDriver>> MAP = new EnumMap<>(RunMode.class);

    static {
        MAP.put(RunMode.LOCAL, LocalWebDriverImpl::new);
        MAP.put(RunMode.REMOTE, RemoteWebDriverImpl::new);
    }


    public static IWebDriver getDriver(RunMode runModeType) {
        return MAP.get(runModeType).get();
    }
}
