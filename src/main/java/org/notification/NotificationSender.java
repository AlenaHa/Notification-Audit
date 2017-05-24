package org.notification;
public interface NotificationSender {

    void sendSms  (String receiverPhoneNumber, String messageId);
    void sendEmail(String recipient, String messageId);
}
