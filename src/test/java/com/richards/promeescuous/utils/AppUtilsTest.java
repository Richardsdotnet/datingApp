package com.richards.promeescuous.utils;

import com.richards.promeescuous.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.richards.promeescuous.utils.AppUtils.generateActivationLink;
import static com.richards.promeescuous.utils.AppUtils.generateToken;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class AppUtilsTest {

    @Autowired
    private AppConfig appConfig;

    @Test
    public void testGenerateActivationLink(){
        String activationLink = generateActivationLink(appConfig.getBaseUrl(), "test@email.com");
        log.info("activation link -->{}", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("http://localhost:8080/activate");
    }


    @Test
    public void generateTokenTest() {
        String email = "test@email.com";
        String token = generateToken(email);
        log.info("generated token--->{}", token);
        assertThat(token).isNotNull();
    }
}