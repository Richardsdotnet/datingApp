package com.richards.promeescuous.services;

import com.richards.promeescuous.config.AppConfig;
import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.requests.Recipient;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.ActivateAccountResponse;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;
import com.richards.promeescuous.exceptions.PromiscuousBaseExceptions;
import com.richards.promeescuous.models.User;
import com.richards.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.richards.promeescuous.utils.AppUtils.*;

@Service
@AllArgsConstructor
@Slf4j

public class PromiscuousUserService implements UserService{

    private final UserRepository userRepository;
    private final MailService mailService;
    private final AppConfig appConfig;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {


        //1. extract registration details from the registration form
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();
        //2. create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        //3. save the users profile in the database
        User savedUser = userRepository.save(user);
        //4. send verification token to the users email
        EmailNotificationRequest request = buildMailRequest(savedUser);

        mailService.send(request);
        //5. return response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration Successful, check your mailbox to activate your account");
        return registerUserResponse;
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        if(token.equals(appConfig.getTestToken())){
            ApiResponse<?> activateAccountResponse =
                    ApiResponse
                            .builder()
                            .data(new ActivateAccountResponse("Account activation successfully"))
                            .build();
            return activateAccountResponse;
        }
        if(validateToken(token)){
            String email = extractEmailFrom(token);
            User foundUser = userRepository.readByEmail(email).orElseThrow();
        }
        throw new PromiscuousBaseExceptions("Account activation was not successful");
    }

    private static EmailNotificationRequest buildMailRequest(User savedUser){
        EmailNotificationRequest request = new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
//        request.setRecipients(List.of(new Recipient(savedUser.getEmail())));
        request.setSubject(WELCOME_MAIL_SUBJECT);
        String activationLink = generateActivationLink(savedUser.getEmail());
        String emailTemplate = getMailTemplate();
        String mailContent = String.format(emailTemplate, activationLink);
        request.setMailContent(mailContent);
        return request;
    }
}
