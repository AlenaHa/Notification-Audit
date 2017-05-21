package com.infouaic.a6;

/**
 * @author Elena Hardon
 * @date 4/10/17.
 */

/**
 *      This class represents the entire module. It is a singleton instance, which is
 *  accesed by {@link com.infouaic.a6.Notification Notification.Instance} with the
 *  following usage: <br></br> <br></br>
 *
 *      SMS SENDING: <br></br>
 *          <p> Notification.Instance.sendSms(recieverPhoneNumber, messageId); </p>
 *      <br></br>
 *      EMAIL SENDING: <br></br>
 *          <p> Notification.Instance.sendEmail(recipient, messageId); </p>
 *
 *
 *
 *
 *
 */
public class Notification implements NotificationSender {

    private MessageType messageType;
    private SmsService  smsService;

    public static Notification Instance = new Notification();

    private Notification() {
        messageType = new MessageType();
        // smsService  = new SmsService();
    }

    /**
     *
     * This method attempts to send a SMS using the {@link  com.infouaic.a6.SmsService;} class
     *
     * @author   RSilviu
     * @date    08.05.2017
     * @param receiverPhoneNumber Represents de destination phone number with country prefix included (RO ex: +40757 *** ***)
     * @param messageId  References predefined message body
     *
     */
    public void sendSms(String receiverPhoneNumber, String messageId) {
        try {
            smsService.createSms(
                    receiverPhoneNumber,
                    messageType.getMessage(messageId)
            );
        } catch (SmsCreateFailException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * This method attempts to send an EMAIL using the {@link  com.infouaic.a6.MailService;} class
     *
     * @author   Grosu Alexandru
     * @param recipient  The receiver email address
     * @param messageId  References predefined message body
     *
     */

    public void sendEmail(String recipient, String messageId) {
        MailService.send(
                recipient,
                messageType.getSubject(),
                messageType.getMessage(messageId)
        );
    }
}
