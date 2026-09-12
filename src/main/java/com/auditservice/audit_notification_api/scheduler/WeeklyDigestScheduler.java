package com.auditservice.audit_notification_api.scheduler;

import com.auditservice.audit_notification_api.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeeklyDigestScheduler {

    private final AuditLogRepository auditLogRepository;

    @Scheduled(cron = "0 0 0 * * SUN")
    public void generateWeeklyDigest() {
        log.info("CRON-START [WeeklyDigestScheduler]: Initiating weekly audit log aggregation...");

        long logCount = auditLogRepository.count();

        log.info("CRON-COMPLETE [WeeklyDigestScheduler]: Total audit records processed: {}", logCount);
    }
}