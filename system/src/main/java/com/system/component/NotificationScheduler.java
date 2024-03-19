package com.system.component;

import com.system.service.IMsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final IMsgService msgService;

    @Scheduled(fixedRate = 30000)
    private void notifyUnreadMessages() {
        msgService.checkAndNotifyUnreadMessages();
    }
}
