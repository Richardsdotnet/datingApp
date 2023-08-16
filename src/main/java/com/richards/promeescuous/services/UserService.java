package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.GetUserResponse;
import com.richards.promeescuous.dtos.responses.LoginResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    void deleteAll();

    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);


    List<GetUserResponse> getAllUsers(int page, int pageSize);

    LoginResponse login(LoginRequest loginRequest);
}
