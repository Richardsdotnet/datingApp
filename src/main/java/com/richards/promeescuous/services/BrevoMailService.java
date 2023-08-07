package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrevoMailService implements MailService{
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "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");
        HttpEntity<EmailNotificationRequest> request = new HttpEntity<>(emailNotificationRequest, headers);

        ResponseEntity<EmailNotificationResponse> response = restTemplate.postForEntity(brevoMailAddress, request, EmailNotificationResponse.class);
        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }
}
