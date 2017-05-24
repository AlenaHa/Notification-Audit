/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public interface NotificationSender {

    void sendSms(long userId, UserType userType, String messageType);

    void sendEmail(long userId, UserType userType, String messageType);
}
