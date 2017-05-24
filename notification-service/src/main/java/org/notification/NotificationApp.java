package org.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by AlenaHa on 25.05.2017.
 */
@SpringBootApplication
public class NotificationApp {
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.config.name", "notification");
        SpringApplication.run(NotificationApp.class);

    }
}