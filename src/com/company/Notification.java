package com.company;

import java.util.List;

/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class Notification implements NotificationSender {

    private MessageType messageType = new MessageType();
    public static Notification Instance = new Notification();

    private List<UserTuple> studentsEmailList;


    private Notification() {

    }

    public List<UserTuple> getStudentsEmailList() {
        return studentsEmailList;
    }

    public void setStudentsEmailList(List<UserTuple> studentsEmailList) {
        this.studentsEmailList = studentsEmailList;
    }

    public void sendSms(long userId, UserType userType, String messageId) {
        System.out.println(userType.toString() + " with id: " + userId + " has received the message: "
                + messageType.getMessage(messageId));
    }

    public void sendEmail(long userId, UserType userType, String messageId) {
        MailSender.send("grosu.alex1995@gmail.com",
                messageType.getSubject(),
                messageType.getMessage(messageId));

        System.out.println(userType.toString() + " with id: " + userId + " has received the e-mail: "
                + messageType.getMessage(messageId));
    }
}
