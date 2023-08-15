package com.richards.promeescuous.utils;

import com.richards.promeescuous.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.richards.promeescuous.utils.AppUtils.generateActivationLink;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class AppUtilsTest {

    private AppConfig appConfig;

    @Test
    public void testGenerateActivationLink(){
        String activationLink = generateActivationLink(appConfig.getBaseUrl(),"test@email.com");
        log.info("activation link --> {} ", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("http://localhost:8080/activate");
    }

    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = JwtUtils.generateToken(email);
        log.info("generated token --> {} ", token);
        assertThat(token).isNotNull();

    }

}
