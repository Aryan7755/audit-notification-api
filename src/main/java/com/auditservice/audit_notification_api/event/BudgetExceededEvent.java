package com.auditservice.audit_notification_api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BudgetExceededEvent {
    private final String userEmail;
    private final String category;
    private final BigDecimal monthlyLimit;
    private final BigDecimal currentSpent;
}