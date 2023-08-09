package com.richards.promeescuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AppUtils {
    public static final String APP_NAME = "promiscuous inc.";
    public static final String APP_EMAIL = "noreply@promiscuous.africa";



    //TODO: Refactor this, remove hardcoded values


    public static String generateActivationLink(String email){
        String baseUrl = "http://localhost:8080";
        String urlActivatePath = "/activate";
        String queryStringPrefix = "?";
        String queryStringKey = "code=";
        String token = generateToken(email);
        String activationLink = baseUrl + urlActivatePath+queryStringPrefix+queryStringKey+token;
        return activationLink;
    }
    public static String generateToken(String email){
        //generate token that has a user's email embedded in it
        String token = JWT.create()
                .withClaim("user", email)
                .withIssuer("promiscuous inc.")
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
    return token;
    }

    public static boolean validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("user")
                .build();
        return verifier.verify(token).getClaim("user") != null;
    }

    public static String extractEmailFrom(String token){


   return  null; }
}
