package org.notification;
import javax.mail.MessagingException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by agrosu on 4/27/2017.
 */
public class MailService {

    public static void send(String to, String subject, String text) {
        final String email = "notificare.info.uaic@gmail.com";
        final String password = "&*notificareinfo*&";

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));

            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(to));

            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
