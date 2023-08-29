package com.richards.promeescuous.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor

public class PromiscuousAuthenticationProvider implements AuthenticationProvider{
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;



        //3. If the password match, request is authenticated
        //4. Else, request isn't authenticated


        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            //1. Take the username from the request (cntained in authentication)
            // and use the userDetailsService to look for a user from the Db with that username.
            String email = authentication.getPrincipal().toString();
            UserDetails user = userDetailsService.loadUserByUsername(email);
            //2 if user from 1 is found, use the passwordEncoder to compare the
            // password from the request to the users password from the DB
            String password = authentication.getCredentials().toString();

            return null;
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return false;
}
    }

