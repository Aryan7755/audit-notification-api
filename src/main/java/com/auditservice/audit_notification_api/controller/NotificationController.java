package com.auditservice.audit_notification_api.controller;

import com.auditservice.audit_notification_api.dto.ApiResponse;
import com.auditservice.audit_notification_api.entity.NotificationLog;
import com.auditservice.audit_notification_api.service.NotificationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationLogService notificationLogService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationLog>>> getAllLogs() {
        return ResponseEntity.ok(ApiResponse.<List<NotificationLog>>builder()
                .success(true)
                .message("Notification logs retrieved successfully")
                .data(notificationLogService.getAllLogs())
                .build());
    }

    @GetMapping("/recipient")
    public ResponseEntity<ApiResponse<List<NotificationLog>>> getLogsByRecipient(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.<List<NotificationLog>>builder()
                .success(true)
                .message("Recipient notification logs retrieved successfully")
                .data(notificationLogService.getLogsByRecipient(email))
                .build());
    }
}