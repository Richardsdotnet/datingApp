package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.requests.Recipient;
import com.richards.promeescuous.dtos.requests.Sender;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import com.richards.promeescuous.services.MailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest

public class MailServiceTest {



    @Autowired
    private MailService mailService;


    @Test
    public void testThatEmailSendingWorks(){

        String recipientEmail = "nicope8821@viperace.com";
        EmailNotificationRequest request = getEmailNotificationRequest(recipientEmail);

        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }

    private static EmailNotificationRequest getEmailNotificationRequest(String recipientEmail) {
        String message = "<p>testing our mail service</p>";
        String subject = "test email";

        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient);


        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipients(recipients);
        request.setSubject(subject);
        return request;
    }
}
