package com.company;
/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class Main {
    public static void main(String[] args) {
        Notification.Instance.sendSms(1222, UserType.STUDENT, MessageType.REQUEST_APPROVED);
        Notification.Instance.sendSms(512, UserType.STUDENT, MessageType.REQUEST_DENIED);
        Notification.Instance.sendSms(222, UserType.SECRETARY, MessageType.REQUEST_FINALISED);
        Notification.Instance.sendSms(312, UserType.SECRETARY, MessageType.REQUEST_RECEIVED);
        Notification.Instance.sendEmail(213, UserType.SECRETARY, MessageType.REQUEST_RECEIVED);
    }
}


