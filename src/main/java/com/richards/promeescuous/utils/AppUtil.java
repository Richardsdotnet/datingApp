package com.richards.promeescuous.utils;

import com.richards.promeescuous.exceptions.PromiscuousBaseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.richards.promeescuous.utils.JwtUtil.generateToken;

public class AppUtil {

    public static final String APP_NAME = "promiscuous inc.";
    public static final String APP_EMAIL = "noreply@promiscuous.africa";

    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\semicolon\\Documents\\spring_projects\\prom-scuous\\src\\main\\resources\\templates\\index.html";

    public static final String WELCOME_MAIL_SUBJECT = "Welcome to promiscuous inc.";

    public static final String BLANK_SPACE=" ";

    public static final String EMPTY_STRING="";

    private static final String ACTIVATE_ACCOUNT_PATH="/user/activate?code=";

    public static String generateActivationLink(String baseUrl, String email){
        String token = generateToken(email);
        //localhost:8080/user/activate?code=xxxxxxxxxxxx
        String activationLink = baseUrl+ACTIVATE_ACCOUNT_PATH+token;
        return activationLink;
    }




    public static String getMailTemplate() {
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContents);
            return template;
        }catch (IOException exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }
    }



}