package com.richards.promeescuous.dtos.responses;
import lombok.*;
@Builder
@Getter@Setter
public record AddressUpdateResponse
    (
            Long id,
            String street,
            String houseNumber,
            String state,
            String country
    ) {
    }



