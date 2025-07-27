package com.caf.automation.web.loggers;

import com.caf.automation.web.enums.loggers.LogType;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.BiConsumer;

/**
 * <p>The LogUtils class provides utility methods for logging operations.</p>
 * <p>
 * This class encapsulates methods to log messages to both Allure and Log4j based on the given LogType.
 * It contains functionalities to log messages of various types like Pass, Fail, and Skip to Allure reports
 * as well as the Log4j logging system.
 * </p>
 *
 * Class:
 * <ul>
 *     <li>{@link LogUtils} - Provides utilities for logging operations using Allure and Log4j.</li>
 * </ul>
 *
 *
 * @since 1.0
 */
public final class LogUtils {
    private LogUtils() {
    }


    private static final Logger logger = LogManager.getLogger(LogUtils.class);


    /**
     *  BiConsumer to perform logging actions based on LogType
     */
    private static final BiConsumer<LogType, String> LOG_ACTION = (logType, message) -> {
        logToAllure(logType, message);
        logToLog4j(logType, message);
    };


    /**
     * Logs a message based on the LogType using Allure and Log4j.
     *
     * @param status  The LogType indicating the status of the log.
     * @param message The message to be logged.
     */
    public static void log(LogType status, String message) {
        LOG_ACTION.accept(status, message);
    }

    /**
     * Logs the message to Allure based on LogType
     * @param status
     * @param message
     */
    private static void logToAllure(LogType status, String message) {
        switch (status) {
            case PASS:
                Allure.addAttachment(String.valueOf(LogType.PASS), message);
                break;
            case FAIL:
                Allure.addAttachment(String.valueOf(LogType.FAIL), message);
                break;
            case SKIP:
                Allure.addAttachment(String.valueOf(LogType.SKIP), message);
                break;
        }

    }


    /**
     *  Logs the message to Log4j based on LogType
     * @param status
     * @param message
     */
    private static void logToLog4j(LogType status, String message) {
        switch (status) {
            case PASS:
                logger.info(message);
                break;
            case FAIL:
                logger.error(message);
                break;
            case SKIP:
                logger.warn(message);
                break;
        }
    }


}
