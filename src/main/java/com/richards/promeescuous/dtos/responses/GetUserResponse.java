
package com.richards.promeescuous.dtos.responses;


import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GetUserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private Long address;
    private String profileImage;




}
