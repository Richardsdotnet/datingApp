package com.richards.promeescuous.security.managers;

import com.richards.promeescuous.exceptions.AuthenticationNotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.richards.promeescuous.exceptions.ExceptionMessage.AUTHENTICATION_NOT_SUPPORTED;


@Component
@AllArgsConstructor
public class PromiscuousAuthenticationManager implements AuthenticationManager{


    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())){
            Authentication authenticationResult = authenticationProvider.authenticate(authentication);
            return authenticationResult;
        }
        throw new AuthenticationNotSupportedException(AUTHENTICATION_NOT_SUPPORTED.getMessage());
    }
}