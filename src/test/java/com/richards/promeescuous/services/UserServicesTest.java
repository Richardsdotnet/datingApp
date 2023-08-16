package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.LoginRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.requests.UpdateUserRequest;
import com.richards.promeescuous.dtos.responses.ApiResponse;
import com.richards.promeescuous.dtos.responses.GetUserResponse;
import com.richards.promeescuous.dtos.responses.LoginResponse;
import com.richards.promeescuous.dtos.responses.UpdateUserResponse;
import com.richards.promeescuous.exceptions.BadCredentialsExceptions;
import com.richards.promeescuous.exceptions.PromiscuousBaseException;
import com.richards.promeescuous.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static com.richards.promeescuous.utils.AppUtils.BLANK_SPACE;
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

    @Test
    public  void testThatUserCanUpdateAccount(){
        UpdateUserRequest updateUserRequest = buildUpdateRequest();
        UpdateUserResponse response = userServices.updateProfile(updateUserRequest, 500L);

        assertThat(response).isNotNull();

        GetUserResponse userResponse = userServices.getUserById(500L);

        String fullName = userResponse.getFullName();
        String expectedFullName = new   StringBuilder()
                .append(updateUserRequest.getFirstName())
                .append(BLANK_SPACE)
                .append(updateUserRequest.getLastName()).toString();
        assertThat(fullName).isEqualTo(expectedFullName);


    }

    private  UpdateUserRequest buildUpdateRequest(){
        Set<String> interests = Set.of("Swimming", "Sports", "Cooking");
        UpdateUserRequest updateRequest = new UpdateUserRequest();
       // updateRequest.setId(500L);
        updateRequest.setFirstName("Richie");
        updateRequest.setLastName("chris");
        updateRequest.setDateOfBirth(LocalDate.of(2000, Month.APRIL.ordinal(),25));
        MultipartFile testImage = getTestImage();
        updateRequest.setProfileImages(testImage);
        updateRequest.setInterests(interests);
        return updateRequest;
    }

    private MultipartFile getTestImage(){
        //obtain a path that points to test image
        Path path = Paths.get("C:\\Users\\PC\\Desktop\\prom-scous\\src\\test\\resources\\images\\creative-logo.jpg");

        //create stream that can read from file pointed to by path
        try (InputStream inputStream = Files.newInputStream(path)){

            //create a multipartfile using bytes from file pointed to by path
            MultipartFile image = new MockMultipartFile("test_image", inputStream);
            return image;
        }catch (Exception exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }
    }

}