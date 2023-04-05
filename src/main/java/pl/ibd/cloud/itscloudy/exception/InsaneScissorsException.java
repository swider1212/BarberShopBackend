package pl.ibd.cloud.itscloudy.exception;

import pl.ibd.cloud.itscloudy.Consts;

public class InsaneScissorsException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "(" + Consts.APP_NAME + " Exception)";

    public InsaneScissorsException() {
        super(prependName(null));
    }

    public InsaneScissorsException(String message) {
        super(prependName(message));
    }

    public InsaneScissorsException(String message, Throwable cause) {
        super(prependName(message), cause);
    }

    private static String prependName(String message) {
        return MESSAGE_PREFIX + (message != null ? " " + message : "");
    }
}
