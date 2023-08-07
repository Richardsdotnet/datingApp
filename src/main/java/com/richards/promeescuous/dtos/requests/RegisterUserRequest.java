package com.richards.promeescuous.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterUserRequest {
    private Sender sender;
    //to
   private List<Recipient> recipient;
   //cc
   private List<String> copiedEmails;
   private String mailContent;
   private String textContent;

}
