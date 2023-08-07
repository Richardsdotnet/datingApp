package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import org.springframework.stereotype.Service;

@Service
public class BrevoMailService implements MailService{
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        return null;
    }
}
