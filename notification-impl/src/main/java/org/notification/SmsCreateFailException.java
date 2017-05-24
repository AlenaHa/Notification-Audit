package org.notification;
/**
 * Created by agrosu on 5/18/2017.
 */
public class SmsCreateFailException extends Exception {
    public SmsCreateFailException(String message) {
        super(message);
    }

    public SmsCreateFailException() {
        super();
    }
}
