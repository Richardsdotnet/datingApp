package com.richards.promeescuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.richards.promeescuous.exceptions.PromiscuousBaseExceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

public class AppUtils{
    public static final String APP_NAME = "promiscuous inc.";
    public static final String APP_EMAIL ="noreply@promiscuous.africa";
    public static final String WELCOME_MAIL_SUBJECT = "Welcome to promiscuous inc.";
    public static String generateToken(String email){
        String token = JWT.create()
                .withClaim("user",email)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
        return token;
    }
    public static String generateActivationLink(String email){
        String baseUrl  =  "http://localhost:8080";
        String urlActivation = "/activate";
        String queryStringPrefix = "?";
        String queryStringKey = "code";
        String token = generateToken(email);
        String activationLink = baseUrl+urlActivation+queryStringPrefix+queryStringKey+token;
        return activationLink;
    }
    public static String getMailTemplate(){
        Path templateLocation = Paths.get("C:\\Users\\DELL\\Downloads\\promiscuous\\promiscuous\\src\\main\\resources\\templates\\Index1.html");
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join("", fileContents);
            return template;
        }
        catch (IOException exception){
            throw new PromiscuousBaseExceptions(exception.getMessage());
        }
    }
    public static boolean validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("User")
                .build();
        return verifier.verify(token).getClaim("user")!=null;
    }
    public static String extractEmailFrom(String token){
        var claim = JWT.decode(token).getClaim("user").asString();
        return claim;
}
}