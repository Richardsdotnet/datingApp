package com.richards.promeescuous.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.richards.promeescuous.utils.AppUtils.APP_EMAIL;
import static com.richards.promeescuous.utils.AppUtils.APP_NAME;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmailNotificationRequest {
    private final Sender sender = new Sender(APP_NAME,APP_EMAIL);

    @JsonProperty("to")
    private List<Recipient> recipient;
    @JsonProperty("cc")
    private List<String> copiedEmails;
    @JsonProperty("htmlContent")
    private String mailContent;
    private String textContent;
    private String subject;

}
