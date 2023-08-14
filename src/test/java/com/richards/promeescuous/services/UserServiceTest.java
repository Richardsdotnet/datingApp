package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;
import com.richards.promeescuous.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private final RegisterUserRequest registerUserRequest = new RegisterUserRequest();

    @BeforeEach
    public void setUp(){
        registerUserRequest.setEmail("nicope8821@viperace.com");
        registerUserRequest.setPassword("password");
    }

    @Test
    public void testThatUserCanRegister(){
        //user fills registration form
        //user submits form by calling register method
        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        RegisterUserResponse response = userService.register(registerUserRequest);
        assertNotNull(response);

        ApiResponse<?> activateUserAccountResponse = userService.activateUserAccount("abc1234.erytuol.67t756");
        assertThat(activateUserAccountResponse).isNotNull();

    }

}
