package com.richards.promeescuous.services;

import com.richards.promeescuous.config.AppConfig;
import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class BrevoMailService implements MailService{
    //TODO: Remove hardcoded values
    private final AppConfig appConfig;
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", appConfig.getMailApiKey());
        headers.set("Content-Type", "application/json");
        HttpEntity<EmailNotificationRequest> request =
                new HttpEntity<>(emailNotificationRequest, headers);

        ResponseEntity<EmailNotificationResponse> response =
                restTemplate.postForEntity(brevoMailAddress, request, EmailNotificationResponse.class);
        EmailNotificationResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }


}