package com.caf.automation.web.converter;

import com.caf.automation.web.enums.Browser;
import lombok.SneakyThrows;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Converts the given input into an Object of type T.</p>
 * The converter is instantiated for every call, so it shouldn't have any internal state.
 */
public class StringToBrowserTypeConverter implements Converter<Browser> {
    /**
     * Converts the given input into an Object of type T.
     * If the method returns null, null will be returned by the Config object.
     * The converter is instantiated for every call, so it shouldn't have any internal state.
     *
     * @param method      the method invoked on the <code>{@link  com.caf.automation.web.config.IConfigurator} object</code>
     * @param browserName the property value specified as input text to be converted to the T return type
     * @return the object of type T converted from the input string.
     * @since 1.0.4
     */
    @SneakyThrows
    @Override
    public Browser convert(Method method, String browserName) {
        Map<String, Browser> stringBrowserTypeMap = Map.of(
                "CHROME", Browser.CHROME,
                "FIREFOX", Browser.FIREFOX);
        
        return stringBrowserTypeMap.getOrDefault(browserName.toUpperCase(), Browser.CHROME);
    }
}
