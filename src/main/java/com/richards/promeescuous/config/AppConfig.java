package com.richards.promeescuous.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${app.dev.token}")
    private String testToken;

}
