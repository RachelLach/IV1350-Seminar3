package se.kth.iv1350.pos.integration.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super("Database access error: " + message);
    }
}
