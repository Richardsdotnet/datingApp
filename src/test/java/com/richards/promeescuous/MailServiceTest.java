package com.richards.promeescuous;

import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
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
        String email = "richardsakaabiam@gmail.com";
        EmailNotificationRequest request = new EmailNotificationRequest();
        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }
}
