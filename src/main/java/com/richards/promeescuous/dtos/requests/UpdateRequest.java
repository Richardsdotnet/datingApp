package com.richards.promeescuous.dtos.requests;

import com.richards.promeescuous.models.Address;
import com.richards.promeescuous.models.Gender;
import com.richards.promeescuous.models.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter

public class UpdateRequest {
    private Long id;
    private LocalDate dateOfBirth;
    private Address address;
    private Gender gender;
    private String firstName;
    private String lastName;
    private Set<String> interests;
    private MultipartFile profileImages;
    private String phoneNumber;
    private String password;

}
