package com.richards.promeescuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AppUtils {

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
}
