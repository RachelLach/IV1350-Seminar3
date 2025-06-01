package se.kth.iv1350.pos.integration.exceptions;

/**
 * Thrown to indicate that a database access error has occurred in the system.
 * This exception is used to simulate or signal database connectivity or access issues
 * in the integration layer of the application.
 */
public class DatabaseException extends Exception {

    /**
     * Creates a new instance of {@code DatabaseException} with a detailed error message.
     * @param message The detail message providing more information about the database error.
     */
    public DatabaseException(String message) {
        super("Database access error: " + message);
    }
}
