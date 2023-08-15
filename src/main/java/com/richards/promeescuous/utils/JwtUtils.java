package com.richards.promeescuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.time.Instant;

import static com.richards.promeescuous.utils.AppUtils.APP_NAME;

public class JwtUtils {
    public static String generateToken(String email){
        //generate token that has the user's email embedded in it

        String token = JWT.create()
                .withClaim("users", email)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
        return token;
    }

    public static boolean validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("user")
                .build();
        return verifier.verify(token) != null;
    }

    public static String extractEmailFrom(String token){
        var claim = JWT.decode(token).getClaim("user");
        return (String) claim.asMap().get("user");

    }
}
