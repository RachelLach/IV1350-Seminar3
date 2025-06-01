package se.kth.iv1350.pos.util;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    @Override
    public void logException(Exception e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        e.printStackTrace(System.out);
    }
}
