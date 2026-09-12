package com.auditservice.audit_notification_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuditLogRequestDTO {
    @NotBlank(message = "Action is required")
    private String action;

    @Email(message = "Invalid email format")
    @NotBlank(message = "User email is required")
    private String userEmail;

    private String details;
}