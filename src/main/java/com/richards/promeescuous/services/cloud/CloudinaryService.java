package com.richards.promeescuous.services.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.richards.promeescuous.config.AppConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;



@Service
@AllArgsConstructor
public class CloudinaryService implements CloudService {
    private final AppConfig appConfig;

    @Override
    public String upload(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary();
        Uploader uploader = cloudinary.uploader();

        try {
            Map<?, ?> response = uploader.upload(file.getBytes(), ObjectUtils.asMap(

                    "public_id", "/", "Promiscuous/assets/uploads" + file.getName(),
                    "api_key", appConfig.getCloudApiKey(),
                    "api_secret", appConfig.getCloudApiSecret(),
                    "cloud_name", appConfig.getCloudApiName(),
                    "secure", true));

            return response.get("url").toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
