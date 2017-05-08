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

    /**
     * @author Silviu Rusu
     * @date 08.05.2017
     *
     * Added functionality to sendSms
     *
     */

    public void sendSms(long userId, UserType userType, String messageId) {
        //  try-catch for handling unpredictable exceptions
        try {
            SmsService ss = new SmsService();   // 3rd party sms gateway(twilio) setup
            String body = messageType.getMessage(messageId);
            //  using personal phone, fetched from os env vars for privacy
            String to = System.getenv("MY_PHONE_NUMBER");
            //  twilio registered phone number
            String from = "+4915735984871";
            boolean allFine = ss.createSms(to, from, body);
            if(allFine)
                System.out.println(userType.toString() + " with id: " + userId + " has received the message: "
                    + body);
            else
                System.out.println("Trouble in sendSms("+userId+", "+userType+", "+messageId+")");
        }
        catch (Exception e){
            System.out.println("Failed to deliver SMS");
            e.printStackTrace();
        }
    }

    public void sendEmail(long userId, UserType userType, String messageId) {
        MailSender.send("grosu.alex1995@gmail.com",
                messageType.getSubject(),
                messageType.getMessage(messageId));

        System.out.println(userType.toString() + " with id: " + userId + " has received the e-mail: "
                + messageType.getMessage(messageId));
    }
}
