package com.example.finalproject.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericExceptionResponse> handleResourceNotFound(
            ResourceNotFoundException ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericExceptionResponse> handleAuthentication(
            AuthenticationException ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED.value(),
                        ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidBookingException.class)
    public ResponseEntity<GenericExceptionResponse> handleInvalidBooking(
            InvalidBookingException ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomUnavailableException.class)
    public ResponseEntity<GenericExceptionResponse> handleRoomUnavailable(
            RoomUnavailableException ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<GenericExceptionResponse> handlePaymentFailed(
            PaymentFailedException ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericExceptionResponse> handleException(
            Exception ex) {

        GenericExceptionResponse response =
                new GenericExceptionResponse(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
