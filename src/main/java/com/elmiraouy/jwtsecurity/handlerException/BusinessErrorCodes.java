package com.elmiraouy.jwtsecurity.handlerException;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0,NOT_IMPLEMENTED,"No code"),
    INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Current passWord is incorrect "),
    ENTITY_NOT_FOUND(400,BAD_REQUEST,"Resource demander n'exist pas "),
    NEW_PASSWORD_NO_MATCH(301,BAD_REQUEST,"The new password does not match "),
    ACCOUNT_LOCKED(302,FORBIDDEN,"User account is locked"),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User account is disabled"),
    BAD_CREDENTIALS(304,FORBIDDEN,"Login or password is incorrect"),
    UNAUTHORIZED(401,HttpStatus.UNAUTHORIZED,"User unauthorized "),

    ;
    private final int code ;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus , String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
