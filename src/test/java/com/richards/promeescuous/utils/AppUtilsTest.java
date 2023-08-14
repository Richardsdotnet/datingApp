package com.richards.promeescuous.utils;

import com.richards.promeescuous.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AppUtilsTest {

    @Test
    public void testGenerateActivationLink(){
        String activationLink = AppUtils.generateActivationLink("test@gmail.com");
        log.info("activationLink ->{}", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("http://localhost:8080/activate");
    }

    @Test
    public void generateTokenTest(){
        String email = "test@gmail.com";
        String token = AppUtils.generateToken(email);
        log.info("generated token->{}", token);
        assertThat(token).isNotNull();
    }
}