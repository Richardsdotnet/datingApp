package com.richards.promeescuous.security.managers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor

public class PromiscuousAuthenticationManager implements AuthenticationManager{

        private  final AuthenticationProvider authenticationProvider;
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            Authentication authenticationResult =  authenticationProvider.authenticate(authentication);
            return authenticationResult;
}
    }

