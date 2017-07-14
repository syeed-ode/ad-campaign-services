package com.comcast.advertisement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@ControllerAdvice
public class AdCampaignExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class, RuntimeException.class})
    protected ResponseEntity<?> handleAllRuntimeExceptions(Exception ex) {
        String bodyOfResponse = ex.getLocalizedMessage();
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyOfResponse);
    }

    @ExceptionHandler(value = AdValidityException.class)
    protected ResponseEntity<?> handleValidityException(AdValidityException ex) {
        String bodyOfResponse = ex.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyOfResponse);
    }
}
