package com.auditservice.audit_notification_api.scheduler;

import com.auditservice.audit_notification_api.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseCleanupScheduler {

    private final AuditLogRepository auditLogRepository;

    // Runs at midnight on the 1st day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanupOldAuditLogs() {
        log.info("CRON JOB EXECUTED: Starting monthly purge of archived audit logs...");

        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(30);

        try {
            auditLogRepository.deleteByTimestampBefore(thresholdDate);
            log.info("Successfully purged audit logs created before: {}", thresholdDate);
        } catch (Exception e) {
            log.error("Failed to cleanup old audit logs: {}", e.getMessage(), e);
        }
    }
}