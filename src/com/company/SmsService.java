package com.company;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Message.Status;
import com.twilio.type.PhoneNumber;

import java.net.URISyntaxException;

/**
 * This class uses the Twilio API to send SMS notifications
 *
 * @author RSilviu
 * @date 08.05.2017
 *
 */

public class SmsService{
    // twilio.com required subscriber data
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SmsService() {
        //  Twilio subscriber credentials check
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * This method attempts to send a SMS to a single recipient using Twilio SMS API
     *
     * !!!!!!!!!!!     ** AVOID EXCESSIVE USE, BECAUSE OF < 10$ TWILIO BALANCE    !!!!!!!!!!!!!
     *
     * ** sending takes about $0.068/sms
     *
     * @param to    Real phone number verified by Twilio, prefixed by country number, e.g. +40 738 123 234
     * @param from  Twilio generated and registered phone number
     * @param body  Body of the SMS
     * @return true, if message was successfully sent, false otherwise
     */
    public boolean createSms(String to, String from, String body){
        Message message = Message
                .creator(new PhoneNumber(to),  // to student
                        new PhoneNumber(from),  // from office / office person
                        body)
                .create();  // send

//        do smth in case of error
        Status msgStatus = message.getStatus();
        if(msgStatus == Status.FAILED || msgStatus == Status.UNDELIVERED)
            return false;
        else
            return true;
    }

    public static void main(String[] args) throws URISyntaxException {
        SmsService smsService = new SmsService();
        //  wrong data on testing purpose
        String to = System.getenv("MY_PHONE_NUMBER");  //   same as from, below,  to (student)
        String from = "+4915735984871";  //   random number, maybe try guessing,   from (office person)
        String body = "ok"; //  empty, image, undefined charset body
        System.out.println(smsService.createSms(to, from, body)?"Succeeded":"Failed");
    }
}