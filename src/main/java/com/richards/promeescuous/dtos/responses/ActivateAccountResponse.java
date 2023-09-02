package com.richards.promeescuous.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ActivateAccountResponse {

    private String message;
    private GetUserResponse user;
}