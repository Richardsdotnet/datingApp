package com.richards.promeescuous.exceptions;


public enum ExceptionMessage {



    USER_NOT_FOUND_EXCEPTION("User no found"),

    USER_WITH_EMAIL_NOT_FOUND_EXCEPTION("user with email %s not found"),

    ACCOUNT_ACTIVATION_FAILED_EXCEPTION("Account activation was not successfully"),
    INVALID_CREDENTIALS_EXCEPTION("Invalid authentication credentials");

    ExceptionMessage(String message){
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    }


