package com.company;

import javax.mail.MessagingException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by agrosu on 4/27/2017.
 */
public class MailSender {

    public static void send(String to, String subject, String text) {
        final String email = "notificare.info.uaic@gmail.com";
        final String password = "&*notificareinfo*&";

        String from = "sonoojaiswal1987@gmail.com";

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

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(text);

            // Send message
            Transport.send(message);
            System.out.println("message sent successfully....");
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
