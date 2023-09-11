package com.asset.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationController {
    /*
    private final NotificationService notificationService;

    @PostMapping
    public void invokeNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("Notification Information " + notificationRequest.toString());

        notificationService.sendMessage(notificationRequest);

    }
    */

}
