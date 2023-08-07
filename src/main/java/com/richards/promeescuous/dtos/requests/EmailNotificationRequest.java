package com.richards.promeescuous.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmailNotificationRequest {
    private Sender sender;
    //to
    private List<Recipient> recipient;
    //cc
    private List<String> copiedEmails;
    private String mailContent;
    private String textContent;
    private String subject;

}
