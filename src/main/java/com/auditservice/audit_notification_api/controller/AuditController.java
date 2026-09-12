package com.auditservice.audit_notification_api.controller;

import com.auditservice.audit_notification_api.dto.ApiResponse;
import com.auditservice.audit_notification_api.entity.AuditLog;
import com.auditservice.audit_notification_api.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/audits")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/trigger/expense")
    public ResponseEntity<ApiResponse<String>> triggerExpense(
            @RequestParam String title,
            @RequestParam BigDecimal amount,
            @RequestParam String category,
            @RequestParam String email) {

        auditService.triggerExpenseEvent(title, amount, category, email);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Expense event published asynchronously")
                .data("Processing in background")
                .build());
    }

    @PostMapping("/trigger/budget")
    public ResponseEntity<ApiResponse<String>> triggerBudget(
            @RequestParam String email,
            @RequestParam String category,
            @RequestParam BigDecimal limit,
            @RequestParam BigDecimal spent) {

        auditService.triggerBudgetEvent(email, category, limit, spent);
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Budget warning event published asynchronously")
                .data("Processing in background")
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuditLog>>> getAllLogs() {
        return ResponseEntity.ok(ApiResponse.<List<AuditLog>>builder()
                .success(true)
                .message("Audit logs retrieved successfully")
                .data(auditService.getAllLogs())
                .build());
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getLogsByUser(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.<List<AuditLog>>builder()
                .success(true)
                .message("User audit logs retrieved successfully")
                .data(auditService.getLogsByUser(email))
                .build());
    }

    @GetMapping("/action")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getLogsByAction(@RequestParam String action) {
        return ResponseEntity.ok(ApiResponse.<List<AuditLog>>builder()
                .success(true)
                .message("Action audit logs retrieved successfully")
                .data(auditService.getLogsByAction(action))
                .build());
    }
}