
/**
 * <p>This package contains configurations and interfaces to manage and retrieve test configuration settings.</p>
 * It provides a structured way to access configuration properties for tests, such as browser type,
 * base URLs, run modes (local or remote), and other environment-specific settings.
 *
 * The main components in this package include:
 * - {@link com.automatrix.config.ConfigFactory}: A factory class that provides access to configurations
 *   by interfacing with the respective configuration interface.
 * - {@link com.automatrix.config.IConfigurator}: An interface defining configuration properties
 *   and their respective getters for the test environment.
 *
 * Configurations are typically sourced from a properties file and merged with system properties
 * and environment variables for flexibility in defining test environment settings.
 * The package employs converters to handle data type conversions when retrieving configurations.
 */
package com.caf.automation.config;

