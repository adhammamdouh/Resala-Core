package org.resala.Exceptions;

import org.resala.Models.Auth.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);



    @ExceptionHandler(value = {NeedToConfirmException.class})
    public ResponseEntity<Object> handleNeedToConfirmException(NeedToConfirmException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MyEntityFoundBeforeException.class})
    public ResponseEntity<Object> handleEntityFoundException(MyEntityFoundBeforeException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.FOUND.value(), ex.getMessage()), HttpStatus.FOUND);
    }
    @ExceptionHandler(value = {AssignedBeforeException.class})
    public ResponseEntity<Object> handleAssignedBeforeException(AssignedBeforeException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MyEntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(MyEntityNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ActiveStateException.class})
    public ResponseEntity<Object> handleDeActivateException(ActiveStateException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.GONE.value(), ex.getMessage()), HttpStatus.GONE);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullException.class})
    public ResponseEntity<Object> handleNullExceptionException(NullException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.PARTIAL_CONTENT.value(), ex.getMessage()), HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleBadCredentialException(AuthenticationException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), "Wrong User Name Or Password"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TokenException.class})
    public ResponseEntity<Object> handleTokenException(TokenException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}