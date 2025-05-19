package se.kth.iv1350.pos.util;

public class FileLogger implements Logger {
    private String filename;

    public FileLogger(String filename) {
        this.filename = filename;
        // initialization code here
    }

    @Override
    public void log(String message) {
        // code to log message to file
    }

    @Override
    public void logException(Exception e) {
        // code to log exception to file
    }
}
