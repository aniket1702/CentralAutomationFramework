package com.caf.automation.web.converter;


import com.caf.automation.web.enums.RemoteMode;
import lombok.SneakyThrows;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * <p>Converts the given input into an Object of type T.</p>
 * The converter is instantiated for every call, so it shouldn't have any internal state.
 */
public class RemoteModeTypeConverter implements Converter<RemoteMode> {
    /**
     * Converts the given input into an Object of type T.
     * If the method returns null, null will be returned by the Config object.
     * The converter is instantiated for every call, so it shouldn't have any internal state.
     *
     * @param method         the method invoked on the <code>{@link org.aeonbits.owner.ConfigFactory} object</code>
     * @param remoteModeType the property value specified as input text to be converted to the T return type
     * @return the object of type T converted from the input string.
     * @since 1.0.4
     */
    @SneakyThrows
    @Override
    public RemoteMode convert(Method method, String remoteModeType) {
        return RemoteMode.valueOf(remoteModeType.toUpperCase());
    }
}
