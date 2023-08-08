package com.richards.promeescuous.services;

import com.richards.promeescuous.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.richards.promeescuous.utils.AppUtils.generateActivationLink;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@Slf4j

public class AppUtilsTest {
     @Test
    public void generateTokenTest(){
         String email = "test@email.com";
         String token = AppUtils.generateToken(email);
         log.info("generated token ->{}", token);
         assertThat(token).isNotNull();
     }

     @Test
    public  void testGenerateActivationLink(){
         String activationLink = generateActivationLink("test@email.com");
         assertThat(activationLink).isNotNull();
         assertThat(activationLink).contains("http://localhost:8080/activate");
     }
}
