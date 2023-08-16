package com.richards.promeescuous.utils;

import com.richards.promeescuous.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.richards.promeescuous.utils.AppUtils.generateActivationLink;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class AppUtilsTest {

    @Autowired
    private AppConfig appConfig;

    @Test
    public void testGenerateActivationLink(){
        String activationLink = generateActivationLink(appConfig.getBaseUrl(),"test@email.com");
        log.info("activation link --> {} ", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("localhost:8080/user/activate");
    }

    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = JwtUtils.generateToken(email);
        log.info("generated token --> {} ", token);
        assertThat(token).isNotNull();

    }

}
