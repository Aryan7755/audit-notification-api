package com.auditservice.audit_notification_api.service;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngine templateEngine;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendBudgetAlertEmail_Success() throws Exception {
        when(templateEngine.process(eq("email/budget-alert"), any(Context.class)))
                .thenReturn("<html>Alert</html>");
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendBudgetAlertEmail("test@example.com", "Food", new BigDecimal("500"), new BigDecimal("600"));

        verify(mailSender, times(1)).send(mimeMessage);
    }
}