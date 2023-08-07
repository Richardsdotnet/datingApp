package com.richards.promeescuous.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class RegisterUserRequest {

private String email;
private String password;
}
