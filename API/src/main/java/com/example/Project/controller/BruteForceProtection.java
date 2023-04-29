package com.example.Project.controller;

import io.micrometer.common.lang.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BruteForceProtection {

    private Map<String, Integer> attempts = new HashMap<>();

    @ExceptionHandler(value = { BruteForceException.class })
    public ResponseEntity<Object> handleBruteForceException(Exception ex, WebRequest request) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ipAddress = attributes.getRequest().getRemoteAddr();
        attempts.put(ipAddress, attempts.getOrDefault(ipAddress, 0) + 1);
        int remainingAttempts = 3 - attempts.getOrDefault(ipAddress, 0);
        if (remainingAttempts > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Retry-After", "30");
            return new ResponseEntity<>("Too many attempts. Please try again in 30 seconds. Remaining attempts: " + remainingAttempts, headers, HttpStatus.TOO_MANY_REQUESTS);
        } else {
            return new ResponseEntity<>("Too many attempts. Please try again later.", HttpStatus.TOO_MANY_REQUESTS);
        }
    }

}