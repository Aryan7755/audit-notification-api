package com.auditservice.audit_notification_api.repository;

import com.auditservice.audit_notification_api.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserEmail(String userEmail);
    List<AuditLog> findByAction(String action);

    @Transactional
    void deleteByTimestampBefore(LocalDateTime timestamp);
}