package com.example.Project.controller;

import com.example.Project.model.BackupData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.CaptchaService;

import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class BackupController {

    private static final int MAX_ATTEMPTS = 5;
    private int failedAttempts = 0;

    private Map<String, String> codes = new ConcurrentHashMap<>();

    CaptchaService captchaService = new CaptchaService("352dda4eb0ab71532fd86633a4097b48");

    @Autowired
    public BackupController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private boolean jsonIsValid(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }

    @PostMapping("/createCode")
    public ResponseEntity<?> createCode(@RequestBody String json, HttpServletRequest request) {
        if (failedAttempts >= MAX_ATTEMPTS) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Too many attempts. Please try again later.");
        }

        String clientIp = getClientIp(request);
        if (!captchaService.verifyCaptcha(clientIp)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Invalid CAPTCHA.");
        }

        if (!jsonIsValid(json)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid JSON format.");
        }

        String code = generateRandomCode();
        codes.put(code, json);

        return ResponseEntity.status(HttpStatus.CREATED).body(code);
    }

    // Метод для проверки кода
    @GetMapping("/checkCode")
    public ResponseEntity<?> checkCode(@RequestParam String code) {
        String json = codes.get(code);

        if (json == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Code not found.");
        }

        codes.remove(code);
        return ResponseEntity.ok(json);
    }
}


