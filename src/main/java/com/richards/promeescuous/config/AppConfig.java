package com.richards.promeescuous.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${app.dev.token}")
    private String testToken;

    @Value("${app.base.url}")
    private String baseUrl;
    @Value("${cloud.api.key}")
    private String cloudApiKey;
    @Value("${cloud.api.name}")
    private String cloudApiName;


    @Value("${cloud.api.secret}")
    private String cloudApiSecret;


    public String getTestToken() {
        return testToken;
    }

    public String getMailApiKey() {
        return mailApiKey;
    }

    public String getBaseUrl() {
        return baseUrl;

    }


    public String getCloudApiKey() {
        return cloudApiKey;
    }

    public String getCloudApiName() {
        return cloudApiName;
    }

    public String getCloudApiSecret() {
        return cloudApiSecret;
    }

}