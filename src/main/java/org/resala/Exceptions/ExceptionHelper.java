package org.resala.Exceptions;

import org.resala.Models.Auth.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class ExceptionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), "User Name Not Found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MyEntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(MyEntityNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {DeActivateException.class})
    public ResponseEntity<Object> handleDeActivateException(DeActivateException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullException.class})
    public ResponseEntity<Object> handleNullExceptionException(NullException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialException(BadCredentialsException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), "Wrong User Name Or Password"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TokenException.class})
    public ResponseEntity<Object> handleTokenException(TokenException ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    /*@ExceptionHandler(value = {HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<Object> handleUserForbiddenException(UsernameNotFoundException ex) {
        LOGGER.error("Forbidden: ", ex.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()), HttpStatus.FORBIDDEN);
    }*/
    /*@ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.error("Invalid Input Exception: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
        LOGGER.error("Unauthorized Exception: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        LOGGER.error("Business Exception: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        LOGGER.error("Exception: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}