package com.richards.promeescuous.dtos.requests;

import com.richards.promeescuous.models.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter

public class UpdateUserRequest {
    private Long id;
    private LocalDate dateOfBirth;
    private String houseNumber;
    private String street;
    private String state;
    private String country;
    private Gender gender;
    private String firstName;
    private String lastName;
    private Set<String> interests;
    private MultipartFile profileImages;
    private String phoneNumber;
    private String password;

}
