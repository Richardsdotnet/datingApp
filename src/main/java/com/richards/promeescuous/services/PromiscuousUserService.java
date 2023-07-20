package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;
import com.richards.promeescuous.models.User;
import com.richards.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@Slf4j

public class PromiscuousUserService implements UserService{

    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        //1. extract registration details from the registration form(registerUserRequest)
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();
        //2. create a user profile with the registration details\
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        //3. save that users profile in the database
        User savedUser = userRepository.save(user);
        //4. send verification token to the uses email
        String emailResponse = MockEmailService.sendEmail(savedUser.getEmail());
        log.info("email sending response -> {}", emailResponse);

        //5. return a response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful, check you email for more information");
        return registerUserResponse;
    }
}
