package com.asset.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notifications",
        url = "${clients.notifications.url}"
        )
public interface NotificationClient {
    @PostMapping("api/v1/notifications")
    public void invokeNotification(@RequestBody NotificationRequest notificationRequest);
}
