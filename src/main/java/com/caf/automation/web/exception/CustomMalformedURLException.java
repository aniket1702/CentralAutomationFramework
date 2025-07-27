package com.caf.automation.web.exception;

/**
 * <p>Custom exception class representing a MalformedURLException during configuration.</p>
 * Extends the RuntimeException to handle specific cases of MalformedURLExceptions
 * within the automation framework.
 */
public class CustomMalformedURLException extends RuntimeException {
    /**
     * Constructs a CustomMalformedURLException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public CustomMalformedURLException(String message, Throwable cause) {
        super(message, cause);
    }

}
