package com.infouaic.a6;

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
    public static final String AUTH_TOKEN  = System.getenv("TWILIO_AUTH_TOKEN");

    public SmsService() {
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
     * @param body  Body of the SMS
     * @return true, if message was successfully sent, false otherwise
     */

    public void createSms(String to, String body) throws SmsCreateFailException {
        String from = "+4915735984871";
        Message message = Message
                .creator(
                        new PhoneNumber(to),
                        new PhoneNumber(from),
                        body)
                .create();

        Status msgStatus = message.getStatus();

        if(msgStatus == Status.FAILED || msgStatus == Status.UNDELIVERED)
            throw new SmsCreateFailException();
    }

    /*public static void main(String[] args) throws URISyntaxException {
        SmsService smsService = new SmsService();

        String to = "+40757940521";
        String body = "ok";

        try {
            smsService.createSms(to, body);
            System.out.println("Success");
        } catch (SmsCreateFailException e) {
            System.out.println("Fail");
        }
    }*/
}