package com.richards.promeescuous.services.cloud;

import com.richards.promeescuous.dtos.responses.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service

public class CloudinaryService implements CloudService{
    @Override
    public ApiResponse<String> upload(MultipartFile file) {
        return null;
    }
}
