package com.richards.promeescuous.services;

import com.richards.promeescuous.dtos.requests.MediaReactionRequest;
import com.richards.promeescuous.dtos.responses.UploadMediaResponse;
import com.richards.promeescuous.models.Media;
import com.richards.promeescuous.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    UploadMediaResponse uploadProfilePicture(MultipartFile file);
    UploadMediaResponse uploadMedia(MultipartFile file, User user);
    String reactToMedia(MediaReactionRequest mediaReactionRequest);

    Media getMediaByUser(User user);
}