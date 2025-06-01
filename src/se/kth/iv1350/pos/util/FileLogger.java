package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code FileLogger} class implements the {@link Logger} interface
 * and provides functionality to log messages and exceptions to a file.
 * This can be used for debugging, auditing, or error tracking in the POS system.
 */
public class FileLogger implements Logger {
    private static FileLogger instance; // Singleton instance
    private static final String DEFAULT_FILENAME = "log.txt"; // Default log file name

    private final String filename;

    /**
     * Private constructor to prevent external instantiation.
     */
    private FileLogger() {
        this.filename = DEFAULT_FILENAME;
    }

    /**
     * Returns the single instance of FileLogger. Creates one if it doesn't exist.
     * @return the single instance of {@code FileLogger}.
     */
    public static FileLogger getInstance() {
        if (instance == null) {
            instance = new FileLogger();
        }
        return instance;
    }

    /**
     * Logs a message to the specified log file.
     * @param message The message to be written to the log file.
     */
    @Override
    public void log(String message) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("LOG: " + message + "\n");
        } catch (IOException e) {
            System.out.println("Could not write log message.");
        }
    }

    /**
     * Logs an exception to the specified log file, including its message.
     * @param e The exception that should be written to the log.
     */
    @Override
    public void logException(Exception e) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("EXCEPTION: " + e.getMessage() + "\n");
        } catch (IOException ioException) {
            System.out.println("Could not write to log file.");
        }
    }
}
