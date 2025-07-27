package com.caf.automation.web.constants;

import com.caf.automation.config.ConfigFactory;
import com.caf.automation.web.enums.Browser;
import com.caf.automation.web.enums.RemoteMode;
import com.caf.automation.web.enums.RunMode;
import com.caf.automation.web.exception.CustomMalformedURLException;
import com.caf.automation.web.utils.EncryptDecryptUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * This class provides constant values sourced from the configuration for automation.
 * It includes browser type, base URL, remote mode, run mode, Selenium Grid URL, and BrowserStack credentials.
 */
public final class CentralAutomationFramework {
    // Prevents instantiation of this class as it is meant to provide constants only.
    private CentralAutomationFramework(){}

    /** The Browser type configured for automation tests. */
    public static final Browser BROWSER = ConfigFactory.getConfig().browser();

    /** The Base URL for the application under test. */
    public static final URL BASE_URL = ConfigFactory.getConfig().baseURL();

    /** The Remote Mode type for running tests. */
    public static final RemoteMode REMOTE_MODE_TYPE = ConfigFactory.getConfig().remoteModeType();

    /** The Run Mode type for executing tests. */
    public static final RunMode RUN_MODE_TYPE = ConfigFactory.getConfig().runModeType();

    /** The Selenium Grid URL used for remote testing. */
    public static final URL SELENIUM_GRID_URL = ConfigFactory.getConfig().seleniumGridURL();

    /** The BrowserStack username for accessing the hub. */
    public static final String BROWSER_STACK_USERNAME = EncryptDecryptUtils.decryptString(ConfigFactory.getConfig().username());

    /** The BrowserStack password for accessing the hub. */
    public static final String BROWSER_STACK_PASSWORD = EncryptDecryptUtils.decryptString(ConfigFactory.getConfig().password());

    /** The URL used to connect to BrowserStack's hub using credentials. */
    public static final URL BROWSER_STACK_URL;

    public static final String TEST_DATA_FILE_NAME = ConfigFactory.getConfig().testDataFileName();
    public static final String TEST_DATA_SHEET_NAME = ConfigFactory.getConfig().sheetName();

    static
    {
        try
        {
            // Constructs the URL using BrowserStack credentials for connecting to their hub.
            String userInfo = BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_PASSWORD;
            URI uri = new URI("https", userInfo, "hub-cloud.browserstack.com", 443, "/wd/hub", null, null);
            BROWSER_STACK_URL = uri.toURL();
        }
        catch (URISyntaxException | MalformedURLException e)
        {
            // Throws a custom exception if failed to create the URL.
            throw new CustomMalformedURLException("Failed to create BROWSER_STACK_URL", e);
        }
    }
}
