package com.richards.promeescuous.services.cloud;

import com.richards.promeescuous.dtos.responses.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile file);
}
