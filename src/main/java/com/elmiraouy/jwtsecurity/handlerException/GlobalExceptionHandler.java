package com.elmiraouy.jwtsecurity.handlerException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;
import static com.elmiraouy.jwtsecurity.handlerException.BusinessErrorCodes.*;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handlerAnyException(Exception exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal error ,contact the admin")
                                .build());
    }
    @ExceptionHandler({LockedException.class})
    public ResponseEntity<ExceptionResponse> handleException(LockedException exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_LOCKED.getCode())
                                .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_DISABLED.getCode())
                                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception){
        log.error("Handled   exception!: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error(BAD_CREDENTIALS.getDescription())
                                .build()
                );
    }
    @ExceptionHandler({MessagingException.class})
    public ResponseEntity<ExceptionResponse> handleException(MessagingException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach(error ->{
            var errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(ENTITY_NOT_FOUND.getDescription())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public ResponseEntity<ExceptionResponse> handleException(InvalidDataAccessApiUsageException  exception){
        log.error("Handled  exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({TypeUnityException.class})
    public ResponseEntity<ExceptionResponse> handleException(TypeUnityException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({UnityException.class})
    public ResponseEntity<ExceptionResponse> handleException(UnityException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({CompanyException.class})
    public ResponseEntity<ExceptionResponse> handleException(CompanyException exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({AppUserException.class})
    public ResponseEntity<ExceptionResponse> handleException(AppUserException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ENTITY_NOT_FOUND.getCode())
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ExceptionResponse> handleException(AuthenticationException  exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(UNAUTHORIZED.getCode())
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handlerAnyException(RuntimeException exception){
        log.error("Handled   exception: {}", exception.getClass().getSimpleName(), exception);
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(500)
                                .businessErrorDescription(exception.getMessage())
                                .error(exception.getMessage())
                                .build()
                );
    }

}
