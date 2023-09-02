package com.richards.promeescuous.services;

import com.github.fge.jsonpatch.JsonPatchException;
import com.richards.promeescuous.dtos.requests.MediaReactionRequest;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.requests.UpdateUserRequest;
import com.richards.promeescuous.dtos.responses.*;
import com.richards.promeescuous.exceptions.UserNotFoundException;
import com.richards.promeescuous.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    ApiResponse<?> activateUserAccount(String token);

    User findUserById(Long id);

    GetUserResponse getUserById(Long id) throws UserNotFoundException;

    List<GetUserResponse> getAllUsers(int page, int pageSize);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id) throws JsonPatchException;

    UploadMediaResponse uploadMedia(MultipartFile mediaToUpload);

    UploadMediaResponse uploadProfilePicture(MultipartFile mediaToUpload);

    ApiResponse<?> reactToMedia(MediaReactionRequest mediaReactionRequest);

    User getUserByEmail(String email);
}