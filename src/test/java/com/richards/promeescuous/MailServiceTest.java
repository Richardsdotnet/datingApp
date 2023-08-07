package com.richards.promeescuous;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.requests.Recipient;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import com.richards.promeescuous.services.MailService;
import com.richards.promeescuous.services.SpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Test

    public void testThatEmailSendingWorks(){
        String recipientEmail = "chris13@gmail.com";
        String message = "Testing our mail service";
        String sender = "noreply@promiscuous.com";
        String email = "richardsakaabiam@gmail.com";
        String subject = "test email";

        Recipient recipient = new Recipient();
        recipient.setMail =

        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipient(recipientEmail);
        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }
}
