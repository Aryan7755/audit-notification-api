package com.auditservice.audit_notification_api.event;

import com.auditservice.audit_notification_api.service.EmailService;
import com.auditservice.audit_notification_api.service.NotificationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final EmailService emailService;
    private final NotificationLogService notificationLogService;

    @Async("taskExecutor")
    @EventListener
    public void handleBudgetExceeded(BudgetExceededEvent event) {
        log.info("Handling notification for BudgetExceededEvent on thread: {}", Thread.currentThread().getName());

        String subject = "⚠️ Budget Alert: Exceeded Limit for " + event.getCategory();
        try {
            emailService.sendBudgetAlertEmail(
                    event.getUserEmail(),
                    event.getCategory(),
                    event.getMonthlyLimit(),
                    event.getCurrentSpent()
            );
            notificationLogService.logSuccess(event.getUserEmail(), subject);
        } catch (Exception e) {
            log.error("Failed to send budget alert email to {}: {}", event.getUserEmail(), e.getMessage());
            notificationLogService.logFailure(event.getUserEmail(), subject, e.getMessage());
        }
    }
}