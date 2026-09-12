package com.auditservice.audit_notification_api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExpenseCreatedEvent {
    private final String title;
    private final BigDecimal amount;
    private final String category;
    private final String userEmail;
}