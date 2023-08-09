package com.richards.promeescuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.richards.promeescuous.exceptions.PromiscuousBaseExceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class AppUtils {
//    public static final String APP_NAME = "promiscuous inc.";
//    public static final String APP_EMAIL = "noreply@promiscuous.africa";
//
//
//
//    //TODO: Refactor this, remove hardcoded values
//
//
//    public static String generateActivationLink(String email){
//        String baseUrl = "http://localhost:8080";
//        String urlActivatePath = "/activate";
//        String queryStringPrefix = "?";
//        String queryStringKey = "code=";
//        String token = generateToken(email);
//        String activationLink = baseUrl + urlActivatePath+queryStringPrefix+queryStringKey+token;
//        return activationLink;
//    }
//    public static String generateToken(String email){
//        //generate token that has a user's email embedded in it
//        String token = JWT.create()
//                .withClaim("user", email)
//                .withIssuer("promiscuous inc.")
//                .withExpiresAt(Instant.now().plusSeconds(3600))
//                .sign(Algorithm.HMAC512("secret"));
//    return token;
//    }
//
//    public static boolean validateToken(String token){
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
//                .withIssuer(APP_NAME)
//                .withClaimPresence("user")
//                .build();
//        return verifier.verify(token).getClaim("user") != null;
//    }
//
//    public static String extractEmailFrom(String token){
//
//
//   return  null; }


    public static final String APP_NAME = "promiscuous inc";
    public static final String APP_EMAIL = "noreply@promiscuous.africa";
    public static final String WELCOME_MAIL_SUBJECT = "Welcome to promiscuous inc.";
    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\USER\\Desktop\\spring\\promiscuous\\src\\main\\resources\\templates\\index.html";

    public static String generateActivationLink(String email){
        String baseUrl = "http://localhost:8080";
        String urlActivatePath = "/activate";
        String queryStringPrefix = "?";
        String queryStringKey = "code=";
        String token = generateToken(email);
        String activationLink = baseUrl+urlActivatePath+queryStringPrefix+queryStringKey+token;

        return activationLink;
    }
    public static String generateToken(String email){
        //generate token that has the user's email embedded in it
        String token = JWT.create()
                .withClaim("user", email)
                .withIssuer("promiscuous inc.")
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));

        return token;
    }
    public static String getMailTemplate(){
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);

        try{
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join("",fileContents);
            return template;
        }catch(IOException exception){
            throw new PromiscuousBaseExceptions(exception.getMessage());
        }
    }

    public static boolean validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("user")
                .build();

        return verifier.verify(token).getClaim("user") != null;
    }

    public static String extractEmailFrom(String token){
        var claim = JWT.decode(token).getClaim("user");
        return (String) claim.asMap().get("user");
    }

}
