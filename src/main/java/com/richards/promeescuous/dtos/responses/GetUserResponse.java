package com.richards.promeescuous.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GetUserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private Long address;
    private String profileImage;
}
