package com.auditservice.audit_notification_api.service;

import com.auditservice.audit_notification_api.entity.AuditLog;
import com.auditservice.audit_notification_api.event.AuditEventPublisher;
import com.auditservice.audit_notification_api.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;
    private final AuditEventPublisher auditEventPublisher;

    public void triggerExpenseEvent(String title, BigDecimal amount, String category, String email) {
        auditEventPublisher.publishExpenseCreated(title, amount, category, email);
    }

    public void triggerBudgetEvent(String email, String category, BigDecimal limit, BigDecimal spent) {
        auditEventPublisher.publishBudgetExceeded(email, category, limit, spent);
    }

    public List<AuditLog> getLogsByUser(String email) {
        return auditLogRepository.findByUserEmail(email);
    }

    public List<AuditLog> getLogsByAction(String action) {
        return auditLogRepository.findByAction(action);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}