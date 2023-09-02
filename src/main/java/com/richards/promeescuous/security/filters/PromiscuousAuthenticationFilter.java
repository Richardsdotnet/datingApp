package com.richards.promeescuous.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richards.promeescuous.exceptions.PromiscuousBaseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

import static com.richards.promeescuous.utils.JwtUtils.generateToken;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Component
@AllArgsConstructor

public class PromiscuousAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //1. Extract the authentication credentials from the request
            InputStream inputStream = request.getInputStream();
            LoginRequest loginRequest = objectMapper.readValue(inputStream, LoginRequest.class);
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            //2. Create an authentication object that is not yet authenticated
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            //3. delegate authentication responsibility of the authentication object to the authentication manager
            Authentication authenticationResult =  authenticationManager.authenticate(authentication);
            //4. put the authenticated object in the SecurityContext

            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        } catch (IOException e) {
            throw new PromiscuousBaseException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String email = authResult.getPrincipal().toString();
        String token = generateToken(email);
        var apiResponse = ApiResponse.builder().data(token);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().print(apiResponse);
    }
}
