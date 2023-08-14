package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.requests.Recipient;
import com.richards.promeescuous.dtos.responses.EmailNotificationResponse;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class MailServiceTest {

//    public MailServiceTest(@Autowired MailService mailService) {
//        this.mailService = mailService;
//    }


    private final MailService mailService = new BrevoMailService() ;


    @Test
    public void testThatEmailSendingWorks(){

        String recipientEmail = "richardsakaabiam@gmail.com";
        String message = "<p>testing our mail service</p>";
//        String mailSender = "noreply@promiscuous";
        String subject = "test email";

        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient);

//        Sender sender = new Sender();
//        sender.setEmail(mailSender);

        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipients(recipients);
        request.setSubject(subject);
//        request.setSender(sender);

        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }
    }

