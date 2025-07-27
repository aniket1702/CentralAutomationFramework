package com.caf.automation.loggers;


import org.apache.logging.log4j.Logger;


/**
 * The LogFactory class provides methods to set a Logger, log informational, error, and warning messages,
 * and unload the Logger from the current thread.
 */
public final class LogFactory {
    private LogFactory() {
    }



    private static final ThreadLocal<Logger> LOG = new ThreadLocal<>();


    /**
     * Sets the Logger instance for the current thread.
     *
     * @param logger The Logger instance to be set for logging.
     */
    public static void setLogger(Logger logger) {
        LOG.set(logger);
    }

    /**
     * Logs an informational message using the configured Logger instance.
     *
     * @param message The informational message to be logged.
     */
    public static void info(String message) {
        Logger logger = LOG.get();
        if (logger != null) {
            logger.info(message);
        }
    }


    /**
     * Logs an error message using the configured Logger instance.
     *
     * @param message The error message to be logged.
     */
    public static void error(String message) {
        Logger logger = LOG.get();
        if (logger != null) {
            logger.error(message);
        }
    }


    /**
     * Logs a warning message using the configured Logger instance.
     *
     * @param message The warning message to be logged.
     */
    public static void warn(String message) {
        Logger logger = LOG.get();
        if (logger != null) {
            logger.warn(message);
        }
    }

    /**
     * Removes the Logger instance from the current thread.
     */
    public static void unLoad() {
        LOG.remove();
    }
}
