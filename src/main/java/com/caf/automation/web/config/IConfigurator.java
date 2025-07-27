package com.caf.automation.web.config;

import com.caf.automation.web.converter.RemoteModeTypeConverter;
import com.caf.automation.web.converter.RunModeTypeConverter;
import com.caf.automation.web.converter.StringToBrowserTypeConverter;
import com.caf.automation.web.converter.StringToUrlTypeConverter;
import com.caf.automation.web.enums.Browser;
import com.caf.automation.web.enums.RemoteMode;
import com.caf.automation.web.enums.RunMode;
import org.aeonbits.owner.Config;

import java.net.URL;


/**
 * Configuration interface defining properties and their types for the application.
 * Extends the Owner Config interface and specifies configuration keys, default values, and converters.
 *
 * <p>{@code IConfigurator} defines various keys and their associated types for the application's configuration.</p>
 * It uses annotations to specify default values, converters, and sources for loading configuration properties.
 *
 * @see Config
 * @since 1.0
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources(
        {
                "system:properties",
                "system:env",
                "file:${user.dir}/src/test/resources/config.properties"
        }
)
public interface IConfigurator extends Config {
    @DefaultValue("CHROME")
    @Key("browser")
    @ConverterClass(StringToBrowserTypeConverter.class)
    Browser browser();

    @Key("baseURL")
    @ConverterClass(StringToUrlTypeConverter.class)
    URL baseURL();

    @Key("runModeType")
    @ConverterClass(RunModeTypeConverter.class)
    RunMode runModeType();

    @Key("remoteModeType")
    @ConverterClass(RemoteModeTypeConverter.class)
    RemoteMode remoteModeType();

    @Key("seleniumGridURL")
    @ConverterClass(StringToUrlTypeConverter.class)
    URL seleniumGridURL();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("testDataFileName")
    String testDataFileName();

    @Key("sheetName")
    String sheetName();
}
