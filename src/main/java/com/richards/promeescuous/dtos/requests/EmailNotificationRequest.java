package com.richards.promeescuous.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

import static com.richards.promeescuous.utils.AppUtils.APP_EMAIL;
import static com.richards.promeescuous.utils.AppUtils.APP_NAME;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmailNotificationRequest {
    private final Sender sender = new Sender(APP_NAME, APP_EMAIL);

    //to
    @JsonProperty("to")
    private List<Recipient> recipients;

    //cc
    @JsonProperty("cc")
    private List<String> copiedEmails;

    //htmlContent
    @JsonProperty("htmlContent")
    private String mailContent;

    private String textContent;

    private String subject;

}