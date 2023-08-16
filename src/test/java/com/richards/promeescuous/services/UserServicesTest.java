package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.GetUserResponse;
import com.richards.promeescuous.dtos.responses.LoginResponse;
import com.richards.promeescuous.exceptions.BadCredentialsExceptions;
import com.richards.promeescuous.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
@Sql(scripts ={"/db/insert.sql"})

public class UserServicesTest {
    @Autowired
    private UserService userServices;

    @Autowired
    private AddressRepository addressRepository;


    @AfterEach
    void setUp() {
    userServices.deleteAll();
    addressRepository.deleteAll();

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
                userServices.activateUserAccount("abc12345.ersfb.35554");
        assertThat(activateUserAccountResponse).isNotNull();

    }

    @Test

    public void getUserByIdTest() {

        GetUserResponse response = userServices.getUserById(500L);
        assertThat(response).isNotNull();
    }

    @Test
    public void getAllUsers(){
        List<GetUserResponse> users = userServices.getAllUsers(1, 5);
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(5);
    }

    @Test
    public void testThatUsersCanLogin() throws BadCredentialsExceptions {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@email.com");
        loginRequest.setPassword("password");


        LoginResponse response = userServices.login(loginRequest);
        assertThat(response).isNotNull();

        String accessToken = response.getAccessToken();
        assertThat(accessToken).isNotNull();
    }

    @Test
    public void testThatExceptionIsThrownWhenUserWhenUserAuthenticatesWithBadCredentials()  {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@email.com");
        loginRequest.setPassword("bad_password");

        assertThatThrownBy(() -> userServices.login(loginRequest))
                .isInstanceOf(BadCredentialsExceptions.class);


    }

}