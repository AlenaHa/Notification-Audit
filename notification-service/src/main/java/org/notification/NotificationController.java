package org.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {

    @RequestMapping(value = "/send-approve-notification")
    public ResponseEntity<?> sendApprovalNotification(@RequestParam String email, @RequestParam String phoneNumber) {
        // TODO: test me with: http://localhost:8777/notification/send-test-email?to=andreea.hardon@gmail.com
        try {
            Notification.Instance.sendEmail(email, MessageType.REQUEST_APPROVED);
            Notification.Instance.sendSms(phoneNumber, MessageType.REQUEST_APPROVED);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/send-denial-notification")
    public ResponseEntity<?> sendDenialNotification(@RequestParam String email, @RequestParam String phoneNumber) {
        // TODO: test me with: http://localhost:8777/notification/send-test-email?to=andreea.hardon@gmail.com
        try {
            Notification.Instance.sendEmail(email, MessageType.REQUEST_DENIED);
            Notification.Instance.sendSms(phoneNumber, MessageType.REQUEST_DENIED);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


}
