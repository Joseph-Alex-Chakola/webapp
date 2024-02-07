package com.csye5.CloudComputing.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;


@Service
public class PasswordManager {


    public String encryptPassword(String password) {
        return BCrypt.hashpw(password.trim(),BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password.trim(), hashedPassword);
    }


    public Map<String, String> decodeBase64(String auth) {
        auth = auth.substring(5).trim();
        byte[] decoded = Base64.getDecoder().decode(auth);
        String credentials = new String(decoded);
        String[] credentialArray = credentials.split(":");
        return Map.of("username", credentialArray[0], "password", credentialArray[1]);
    }
}
