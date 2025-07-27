package com.caf.automation.web.converter;

import com.caf.automation.web.enums.RunMode;
import lombok.SneakyThrows;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * <p>Converts the given input into an Object of type T.</p>
 * The converter is instantiated for every call, so it shouldn't have any internal state.
 */
public class RunModeTypeConverter implements Converter<RunMode> {
    /**
     * Converts the given input into an Object of type T.
     * If the method returns null, null will be returned by the Config object.
     * The converter is instantiated for every call, so it shouldn't have any internal state.
     *
     * @param method      the method invoked on the <code>{@link com.caf.automation.web.config.IConfigurator} object</code>
     * @param runModeType the property value specified as input text to be converted to the T return type
     * @return the object of type T converted from the input string.
     * @since 1.0.4
     */
    @SneakyThrows
    @Override
    public RunMode convert(Method method, String runModeType) {
        return RunMode.valueOf(runModeType.toUpperCase());
    }
}
