package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.service.UserVerificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserVerificationController {

    private UserVerificationService userVerificationService;
    private static final Logger logger = LoggerFactory.getLogger(UserVerificationController.class);

    @GetMapping("/verify")
    public void verifyUser(@RequestParam String token) {
        logger.info("Verifying user with token: " + token);
        userVerificationService.verifyUser(token);
    }
}
