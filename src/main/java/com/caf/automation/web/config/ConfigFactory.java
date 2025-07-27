package com.caf.automation.web.config;

import org.aeonbits.owner.ConfigCache;


/**
 * Factory class responsible for providing configuration instances.
 * Utilizes Owner library to manage and retrieve configurations.
 *
 * <p>{@code ConfigFactory} provides a method to retrieve an instance
 * of the {@link IConfigurator} interface representing the application's configuration.
 *
 * @since 1.0
 */
public final class ConfigFactory {
    private ConfigFactory(){}

    /**
     * Retrieves an instance of the application's configuration.
     *
     * @return An instance of the {@link IConfigurator} interface.
     */
public static IConfigurator getConfig()
{
    return ConfigCache.getOrCreate(IConfigurator.class);
}
}
