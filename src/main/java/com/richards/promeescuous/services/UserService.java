package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.requests.UpdateUserRequest;
import com.richards.promeescuous.dtos.responses.*;
import com.richards.promeescuous.exceptions.BadCredentialsExceptions;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    void deleteAll();

    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);


    List<GetUserResponse> getAllUsers(int page, int pageSize);

    LoginResponse login(LoginRequest loginRequest) throws BadCredentialsExceptions;

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);
}
