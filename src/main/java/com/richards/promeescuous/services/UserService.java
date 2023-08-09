package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    ApiResponse<?> activateUserAccount(String token);
}
