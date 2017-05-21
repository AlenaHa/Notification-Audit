package com.infouaic.a6;

/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public interface NotificationSender {

    void sendSms  (String receiverPhoneNumber, String messageId);
    void sendEmail(String recipient, String messageId);
}
