package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.GetUserResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;
import com.richards.promeescuous.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
@Sql(scripts ={"/db/insert.sql"})

public class UserServicesTest {
    @Autowired
    private UserService userServices;


    @BeforeEach
    void setUp() {

    }

    @Test
    public void testThatUserCanRegister() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("rich@gmail");
        registerUserRequest.setPassword("password");
        var registerUserResponse = userServices.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount() {
        ApiResponse<?> activateUserAccountResponse =
                userServices.activateUserAccount("abc1234.erytuuoi.67t75646");
        assertThat(activateUserAccountResponse).isNotNull();

    }

    @Test

    public void getUserByIdTest() {

        GetUserResponse response = userServices.getUserById(1L);
        assertThat(response).isNotNull();
    }

    @Test
    public void getAllUsers(){
        List<GetUserResponse> users = userServices.getAllUsers(1, 5);
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(5);
    }}