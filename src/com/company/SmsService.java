package com.company;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Message.Status;
import com.twilio.type.PhoneNumber;

import java.net.URISyntaxException;

/**
 * @author Silviu Rusu
 * @date 08.05.2017
 *
 *  TODO:
 *      -create failing tests
 *
 *      -consider using twilio messaging service sms notify features, comprising message organization, which might
 *      unnecessarily complicate things though, links:
 *
 *    https://www.twilio.com/docs/api/notify/guides/sms-quickstart
 *    https://www.twilio.com/docs/api/notify/guides/sending-notifications#server-sending-notifications
 *
 * */

/*
***************************README**************************
*
* Prerequisites:
* twilio free account +
* follow tutorial at https://www.twilio.com/docs/quickstart/java/devenvironment?utm_campaign=&utm_medium=email&utm_source=&utm_content=REG_SUP_2017-MAR-16_WW_EM_1_Developer_SMS&utm_term=&elqTrackId=d535343293ec4fc4b05145fabaaf2c77&elq=15c9e87cc78f475c903bcf876b113947&elqaid=3641&elqat=1&elqCampaignId=1781#install-java
*
* */
public class SmsService{
    // twilio.com required subscriber data
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SmsService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    //  effectively sends sms;
    //  to and from are phone numbers conforming to twilio.com trial account terms
    //  which means one number is twilio registered and generated, while the other is any number
    //  verified on twilio platform, order independent
    //  !!!!!!!!!!!     ** AVOID EXCESSIVE USE, BECAUSE OF < 10$ TWILIO BALANCE    !!!!!!!!!!!!!
    //  ** still sending takes about $0.068

    public boolean createSms(String to, String from, String body){
        Message message = Message
                .creator(new PhoneNumber(to),  // to student
                        new PhoneNumber(from),  // from office / office person
                        body)
                .create();

//        do smth in case of error
        Status msgStatus = message.getStatus();
        if(msgStatus == Status.FAILED || msgStatus == Status.UNDELIVERED)
            return false;
        else
            return true;
    }

    public static void main(String[] args) throws URISyntaxException {
        SmsService ss = new SmsService();
        //  wrong data on purpose
        String to = System.getenv("MY_PHONE_NUMBER");  //   same as from, below,  to (student)
        String from = "+4915735984871";  //   random number, maybe try guessing,   from (office person)
        String body = "ok"; //  empty, image, undefined charset body
        System.out.println(ss.createSms(to, from, body)?"Succeeded":"Failed");
    }
}