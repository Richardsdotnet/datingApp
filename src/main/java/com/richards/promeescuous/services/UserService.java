package com.richards.promeescuous.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.requests.UpdateUserRequest;
import com.richards.promeescuous.dtos.responses.*;
import com.richards.promeescuous.exceptions.BadCredentialsExceptions;
import com.richards.promeescuous.models.Interest;
import com.richards.promeescuous.models.User;

import javax.xml.stream.Location;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    void deleteAll();

    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);

    LoginResponse login(LoginRequest loginRequest) throws BadCredentialsExceptions;

    UpdateUserResponse updateUserProfile(JsonPatch jsonPatch, Long id);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);


    List<User> suggestUserByInterest(Long userId);

}
