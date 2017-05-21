package com.infouaic.a6;
/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class Main {
    public static void main(String[] args) {
        // Notification.Instance.sendSms(1222, UserType.STUDENT, MessageType.REQUEST_APPROVED);
        Notification.Instance.sendEmail("grosu.alex1995@gmail.com", MessageType.REQUEST_RECEIVED);
    }
}
