package com.auditservice.audit_notification_api.service;

import com.auditservice.audit_notification_api.entity.NotificationLog;
import com.auditservice.audit_notification_api.entity.NotificationLog.NotificationStatus;
import com.auditservice.audit_notification_api.repository.NotificationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationLogService {

    private final NotificationLogRepository repository;

    public void logSuccess(String recipient, String subject) {
        NotificationLog log = NotificationLog.builder()
                .recipient(recipient)
                .subject(subject)
                .status(NotificationStatus.SENT)
                .sentAt(LocalDateTime.now())
                .build();
        repository.save(log);
    }

    public void logFailure(String recipient, String subject, String error) {
        NotificationLog log = NotificationLog.builder()
                .recipient(recipient)
                .subject(subject)
                .status(NotificationStatus.FAILED)
                .errorMessage(error)
                .sentAt(LocalDateTime.now())
                .build();
        repository.save(log);
    }

    public List<NotificationLog> getAllLogs() {
        return repository.findAll();
    }

    public List<NotificationLog> getLogsByRecipient(String recipient) {
        return repository.findByRecipient(recipient);
    }
}