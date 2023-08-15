package com.richards.promeescuous.utils;

import com.richards.promeescuous.exceptions.PromiscuousBaseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.richards.promeescuous.utils.JwtUtil.generateToken;


public class AppUtils {

    public static final String APP_NAME = "promisuous inc.";

    public static final String APP_EMAIL = "noreply@promiscuous.africa";

    public static final String WELCOME_MESSAGE = "Welcome to promiscuous inc.";

    public static final String BLANK_SPACE = " ";

    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\Admin\\promeescuous\\src\\main\\resources\\templates\\index.html";

    private static final String EMPTY_STRING = "";

    private static final String ACTIVATE_ACCOUNT_PATH = "/user/activate?code=";

    public static String generateActivationLink(String baseUrl, String email){

        String token = generateToken(email);
        String activationLink = baseUrl+ ACTIVATE_ACCOUNT_PATH + token;


        return activationLink;
    }



    public static String getMailTemplate(){
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);

        try {
            List<String> fileContent = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContent);

            return template;

        }catch (IOException exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }

    }


}
