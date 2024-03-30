package com.csye5.CloudComputing.service;

import com.csye5.CloudComputing.controller.UserVerificationController;
import com.csye5.CloudComputing.dao.UserDao;
import com.csye5.CloudComputing.repository.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserVerificationService {

    private static final Logger logger = LoggerFactory.getLogger(UserVerificationService.class);
    UserDao userDao;

    public void verifyUser(String token) {
        User user = userDao.findById(token).orElseThrow();
        if(user.isVerified()) {
            logger.info("User already verified: " + user.getUsername());
            return;
        }

        Timestamp timestamp = user.getVerificationTime();
        Timestamp current = Timestamp.from(Instant.now());
        long ms = current.getTime() - timestamp.getTime();

        if (ms > 120000) {
            logger.error("Token expired: " + user.getUsername());
            userDao.delete(user);
            return;
        } else {
            user.setVerified(true);
            logger.info("Token verified: " + user.getUsername());
        }

        userDao.save(user);
        logger.info("User verified: " + user.getUsername());
    }
}
