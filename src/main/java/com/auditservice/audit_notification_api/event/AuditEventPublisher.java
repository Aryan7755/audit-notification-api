package com.auditservice.audit_notification_api.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AuditEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishExpenseCreated(String title, BigDecimal amount, String category, String userEmail) {
        ExpenseCreatedEvent event = new ExpenseCreatedEvent(title, amount, category, userEmail);
        applicationEventPublisher.publishEvent(event);
    }

    public void publishBudgetExceeded(String userEmail, String category, BigDecimal monthlyLimit, BigDecimal currentSpent) {
        BudgetExceededEvent event = new BudgetExceededEvent(userEmail, category, monthlyLimit, currentSpent);
        applicationEventPublisher.publishEvent(event);
    }
}