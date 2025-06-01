package se.kth.iv1350.pos.util;
/**
 * The {@code Logger} interface defines a standard contract for logging functionality.
 * Implementing classes should provide ways to log messages and exceptions, such as to a file,
 * console, or remote server.
 */
public interface Logger {

    /**
     * Logs a plain text message.
     *
     * @param message The message to be logged.
     */
    void log(String message);

    /**
     * Logs an exception, typically including its message and stack trace.
     *
     * @param e The exception to be logged.
     */
    void logException(Exception e);
}

