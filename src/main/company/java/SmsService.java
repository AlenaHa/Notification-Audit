import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Message.Status;
import com.twilio.type.PhoneNumber;

public class SmsService {

    // twilio.com required subscriber data
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SmsService() {
        //  Twilio subscriber credentials check
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static void main(String[] args) throws URISyntaxException {
        SmsService smsService = new SmsService();
        //  wrong data on testing purpose

        //   same as from, below,  to (student)
        String to = System.getenv("MY_PHONE_NUMBER");

        //   random number, maybe try guessing,   from (office person)
        String from = "+4915735984871";
        //  empty, image, undefined charset body
        String body = "ok";
        System.out.println(smsService.createSms(to, from, body) ? "Succeeded" : "Failed");
    }

    /**
     * This method attempts to send a SMS to a single recipient using Twilio SMS API
     *
     * @param to   Real phone number verified by Twilio, prefixed by country number, e.g. +40 738 123 234
     * @param from Twilio generated and registered phone number
     * @param body Body of the SMS
     *
     * @return true, if message was successfully sent, false otherwise
     */
    public boolean createSms(String to, String from, String body) {
        Message message = Message
                .creator(new PhoneNumber(to),  // to student
                        new PhoneNumber(from),  // from office / office person
                        body)
                .create();  // send

//        do smth in case of error
        Status msgStatus = message.getStatus();
        if (msgStatus == Status.FAILED || msgStatus == Status.UNDELIVERED)
            return false;
        else
            return true;
    }
}