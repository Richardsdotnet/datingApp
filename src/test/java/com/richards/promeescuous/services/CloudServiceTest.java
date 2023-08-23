package com.richards.promeescuous.services;

import com.richards.promeescuous.services.cloud.CloudService;
import com.richards.promeescuous.utils.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class CloudServiceTest {
    @Autowired
    private CloudService cloudService;


    @Test

    public void testUploadFile(){
        Path path = Paths.get(AppUtils.TEST_IMAGE_LOCATION);
        try (
            InputStream inputStream = Files.newInputStream(path)){
                MultipartFile file = new MockMultipartFile("testImages", inputStream);
                String response = cloudService.upload(file);
                assertNotNull(response);
                assertThat(response).isNull();

        } catch (IOException e) {
            throw new RuntimeException(":(");
        }
    }
}
