package org.notification;

import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AlenaHa on 25.05.2017.
 */
@RestController
public class NotificationController {

    @RequestMapping(value = "/send-test-email")
    public ResponseEntity<?> sendTestNotification(@RequestParam String to) {
        // TODO: test me with: http://localhost:8777/notification/send-test-email?to=andreea.hardon@gmail.com
        try {
            Notification.Instance.sendEmail(to, MessageType.REQUEST_RECEIVED);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
