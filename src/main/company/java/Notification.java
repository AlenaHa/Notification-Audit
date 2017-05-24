import java.util.List;

public class Notification implements NotificationSender {

    public static Notification Instance = new Notification();
    private MessageType messageType = new MessageType();
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
     * This method attempts to send a SMS using the {@link  SmsService} class
     *
     * @param userId    The user id of the recipient
     * @param userType  The user type of the recipient
     * @param messageId References predefined message body
     *
     * @author RSilviu
     * @date 08.05.2017
     */
    public void sendSms(long userId, UserType userType, String messageId) {
        //  try-catch for handling unpredictable exceptions
        try {
            // 3rd party sms gateway(twilio) setup
            SmsService smsService = new SmsService();
            String body = messageType.getMessage(messageId);
            //  using personal phone, fetched from os env vars for privacy
            //  can be replaced by number from db, after twilio verification

            String to = System.getenv("MY_PHONE_NUMBER");
            //  twilio registered phone number
            String from = "+4915735984871";
            boolean allFine = smsService.createSms(to, from, body);
            if (allFine)
                System.out.println(userType.toString() + " with id: " + userId + " has received the message: "
                        + body);
            else
                System.out.println("Trouble in sendSms(" + userId + ", " + userType + ", " + messageId + ")");
        } catch (Exception e) {
            System.out.println("Failed to deliver SMS");
            e.printStackTrace();
        }
    }

    public void sendEmail(long userId, UserType userType, String messageId) {
        MailSender.send("andreea.hardon@gmail.com",
                messageType.getSubject(),
                messageType.getMessage(messageId));

        System.out.println(userType.toString() + " with id: " + userId + " has received the e-mail: "
                + messageType.getMessage(messageId));
    }
}
