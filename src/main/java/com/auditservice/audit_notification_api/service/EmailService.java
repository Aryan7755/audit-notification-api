package com.auditservice.audit_notification_api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Retryable(
            retryFor = { MessagingException.class, Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void sendBudgetAlertEmail(String recipient, String category, BigDecimal limit, BigDecimal spent) throws MessagingException {
        log.info("Preparing budget alert email for recipient: {}", recipient);

        Context context = new Context();
        context.setVariable("category", category);
        context.setVariable("limit", limit);
        context.setVariable("spent", spent);

        String htmlContent = templateEngine.process("email/budget-alert", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(recipient);
        helper.setSubject("⚠️ Budget Alert: Exceeded Limit for " + category);
        helper.setText(htmlContent, true);

        mailSender.send(message);
        log.info("Budget alert email successfully sent to: {}", recipient);
    }
}