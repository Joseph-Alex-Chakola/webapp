package com.csye5.CloudComputing.service;


import com.csye5.CloudComputing.controller.UserController;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;


@Service
public class PasswordManager {

    private static final Logger logger = LoggerFactory.getLogger(PasswordManager.class);

    public String encryptPassword(String password) {
        logger.info("Encrypting password");
        return BCrypt.hashpw(password.trim(),BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        logger.info("Checking password");
        return BCrypt.checkpw(password.trim(), hashedPassword);
    }


    public Map<String, String> decodeBase64(String auth) {
        logger.info("Decoding base64");
        auth = auth.substring(5).trim();
        byte[] decoded = Base64.getDecoder().decode(auth);
        String credentials = new String(decoded);
        String[] credentialArray = credentials.split(":");
        return Map.of("username", credentialArray[0], "password", credentialArray[1]);
    }
}
