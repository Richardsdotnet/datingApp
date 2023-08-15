package com.richards.promeescuous.dtos.requests;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Sender {
    private String name;

    private String email;
}