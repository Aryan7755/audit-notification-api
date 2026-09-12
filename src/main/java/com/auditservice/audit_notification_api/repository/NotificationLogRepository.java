package com.auditservice.audit_notification_api.repository;

import com.auditservice.audit_notification_api.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
    List<NotificationLog> findByRecipient(String recipient);
    List<NotificationLog> findByStatus(NotificationLog.NotificationStatus status);
}