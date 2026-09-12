package com.auditservice.audit_notification_api.event;

import com.auditservice.audit_notification_api.entity.AuditLog;
import com.auditservice.audit_notification_api.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditEventListener {

    private final AuditLogRepository auditLogRepository;

    @Async("taskExecutor")
    @EventListener
    public void handleExpenseCreated(ExpenseCreatedEvent event) {
        log.info("EVENT-RECEIVED [Thread: {}]: Action=EXPENSE_CREATED, TargetUser={}",
                Thread.currentThread().getName(), event.getUserEmail());

        String details = String.format("Created expense '%s' of amount %s in category '%s'",
                event.getTitle(), event.getAmount(), event.getCategory());

        AuditLog auditLog = AuditLog.builder()
                .action("EXPENSE_CREATED")
                .userEmail(event.getUserEmail())
                .details(details)
                .build();

        auditLogRepository.save(auditLog);
        log.info("AUDIT-PERSISTED [LogID: {}]: Successfully recorded expense creation", auditLog.getId());
    }

    @Async("taskExecutor")
    @EventListener
    public void handleBudgetExceeded(BudgetExceededEvent event) {
        log.info("EVENT-RECEIVED [Thread: {}]: Action=BUDGET_EXCEEDED, TargetUser={}",
                Thread.currentThread().getName(), event.getUserEmail());

        String details = String.format("Exceeded limit for '%s': Spent %s out of limit %s",
                event.getCategory(), event.getCurrentSpent(), event.getMonthlyLimit());

        AuditLog auditLog = AuditLog.builder()
                .action("BUDGET_EXCEEDED")
                .userEmail(event.getUserEmail())
                .details(details)
                .build();

        auditLogRepository.save(auditLog);
        log.info("AUDIT-PERSISTED [LogID: {}]: Successfully recorded budget threshold breach", auditLog.getId());
    }
}