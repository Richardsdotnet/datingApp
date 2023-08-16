package com.richards.promeescuous.services;

import com.richards.promeescuous.config.AppConfig;
import com.richards.promeescuous.dtos.requests.EmailNotificationRequest;
import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.Recipient;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.*;
import com.richards.promeescuous.exceptions.AccountActivationFailedException;
import com.richards.promeescuous.exceptions.BadCredentialsExceptions;
import com.richards.promeescuous.exceptions.UserNotFoundException;
import com.richards.promeescuous.models.Address;
import com.richards.promeescuous.models.User;
import com.richards.promeescuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.richards.promeescuous.dtos.responses.ResponseMessage.ACCOUNT_ACTIVATION_SUCCESSFUL;
import static com.richards.promeescuous.dtos.responses.ResponseMessage.USER_REGISTRATION_SUCCESSFUL;
import static com.richards.promeescuous.exceptions.ExceptionMessage.*;
import static com.richards.promeescuous.utils.AppUtils.*;
import static com.richards.promeescuous.utils.JwtUtils.*;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserService{
    private final UserRepository userRepository;

    private final MailService mailServices;

    private final AppConfig appConfig;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(new Address());
        User savedUser = userRepository.save(user);
        EmailNotificationRequest request = buildEmailRequest(savedUser);
        log.info("email:::{}",request);
        mailServices.send(request);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage(USER_REGISTRATION_SUCCESSFUL.name());
        return registerUserResponse;
    }

    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        boolean isTestToken = token.equals(appConfig.getTestToken());
        if(isTestToken) return activateTestAccount();
        boolean isValidJwt = isValidateToken(token);

        if (isValidJwt) return activateAccount(token);
        throw new AccountActivationFailedException(ACCOUNT_ACTIVATION_FAILED_EXCEPTION.getMessage());

    }

    @Override
    public GetUserResponse getUserById(Long id) {
        Optional<User> found = userRepository.findById(id);
        User user = found.orElseThrow(
                ()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage())
        );
        GetUserResponse getUserResponse = buildGetUserResponse(user);
        return getUserResponse;
    }

    @Override
    public List<GetUserResponse> getAllUsers(int page, int pageSize) {
        List<GetUserResponse.GetUserResponse> users = new ArrayList<>();
        Pageable pageable = buildPageRequest(page, pageSize);
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> foundUsers = usersPage.getContent();

//        for (User user: foundUsers){
//            GetUserResponse getUserResponse = buildGetUserResponse(user);
//            users.add(getUserResponse);
//        }
//        return users
        return foundUsers.stream()
                .map(PromiscuousUserService ::buildGetUserResponse)
                .toList();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws BadCredentialsExceptions {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> foundUser = userRepository.readByEmail(email);
        User user = foundUser.orElseThrow(() -> new UserNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
        ));
        boolean isValidPassword = matches(user.getPassword(),password);
        if(isValidPassword) return buildLoginResponse(email);
        throw new BadCredentialsExceptions(INVALID_CREDENTIALS_EXCEPTION.getMessage() );

        }

    private LoginResponse buildLoginResponse(String email) {
        String accessToken = generateToken(email);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        return loginResponse;
    }


    private Pageable buildPageRequest(int page, int pageSize) {
        if (page<1&&pageSize<1)return PageRequest.of(0,10);
        if (page<1)return  PageRequest.of(0,pageSize);
        if (pageSize<1)return PageRequest.of(page,pageSize);
        return PageRequest.of(page-1, pageSize);
    }


    private ApiResponse<?> activateAccount(String token) {
        String email = extractEmailFrom(token);
        Optional<User> user = userRepository.findByEmail(email);
        User foundUser = user.orElseThrow(
                ()-> new UserNotFoundException(
                        String.format(USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
                ));

        foundUser.setActive(true);
        User savedUser = userRepository.save(foundUser);
        GetUserResponse.GetUserResponse userResponse = buildGetUserResponse(savedUser);
        var activateUserResponse = buildActivationUserResponse(userResponse);
        return ApiResponse.builder().data(activateUserResponse).build();


    }

    private static ActivateAccountResponse buildActivationUserResponse(GetUserResponse.GetUserResponse userResponse){
        return ActivateAccountResponse.builder()
                .message(ACCOUNT_ACTIVATION_SUCCESSFUL.name())
                .user(userResponse)
                .build();
    }

    private static GetUserResponse.GetUserResponse buildGetUserResponse(User savedUser){
        return GetUserResponse.GetUserResponse.builder()
                .id(savedUser.getId())
                .address(savedUser.getAddress().toString())
                .fullName(getFullName(savedUser))
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .build();
    }

    private static String getFullName(User savedUser){
        return savedUser.getFirstName() + BLANK_SPACE + savedUser.getLastName();
    }

    private static ApiResponse<?> activateTestAccount() {
        ApiResponse<?> activateAccountResponse =
                ApiResponse.builder()
                        .build();

        return activateAccountResponse;
    }

    private EmailNotificationRequest buildEmailRequest(User savedUser){
        EmailNotificationRequest request =new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
        request.setSubject(WELCOME_MESSAGE);
        String activationLink =
                generateActivationLink(appConfig.getBaseUrl(), savedUser.getEmail());
        String emailTemplate = getMailTemplate();

        String mailContent = String.format(emailTemplate, activationLink);

        request.setMailContent(mailContent);

        return request;
    }
}