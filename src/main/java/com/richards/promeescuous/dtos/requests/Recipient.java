package com.richards.promeescuous.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
