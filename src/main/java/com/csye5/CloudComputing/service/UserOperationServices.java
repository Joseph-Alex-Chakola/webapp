package com.csye5.CloudComputing.service;

import com.csye5.CloudComputing.Exception.UserAlreadyExistsException;
import com.csye5.CloudComputing.controller.UserController;
import com.csye5.CloudComputing.dao.UserDao;
import com.csye5.CloudComputing.model.UserModel;
import com.csye5.CloudComputing.model.UserResponseModel;
import com.csye5.CloudComputing.repository.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserOperationServices {

    UserDao userDao;
    PasswordManager passwordManager;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserResponseModel getUserDetails(String auth) throws AccessDeniedException {
        Map<String,String> credentials =  passwordManager.decodeBase64(auth);
        User user = userDao.findUserByUsername(credentials.get("username"))
                .orElseThrow();
        logger.info("User found");
        if(!user.isVerified()){
            logger.error("User not verified");
            throw new AccessDeniedException("User not verified");
        }
        if(passwordManager.checkPassword(credentials.get("password"), user.getPassword())){
            UserResponseModel result = new UserResponseModel();
            result.setId(user.getId());
            result.setFirst_name(user.getFirstName());
            result.setLast_name(user.getLastName());
            result.setUsername(user.getUsername());
            result.setAccount_created(user.getAccountCreated());
            result.setAccount_updated(user.getAccountUpdated());
            logger.info("User details returned");
            return result;
        }else {
            logger.error("Password doesn't match");
            throw new NoSuchElementException("Password doesn't match");
        }

    }

    public void updateUserDetails(String auth, UserModel userModel) throws AccessDeniedException {

        if(userModel.getUsername()!=null){
            throw new IllegalArgumentException("Username cannot be updated");
        }

        Map<String,String> credentials =  passwordManager.decodeBase64(auth);
        User user = userDao.findUserByUsername(credentials.get("username")).orElseThrow();

        if(!user.isVerified()){
            logger.error("User not verified");
            throw new AccessDeniedException("User not verified");
        }
        if(passwordManager.checkPassword(credentials.get("password"), user.getPassword())) {
            if (userModel.getFirst_name() != null) {
                user.setFirstName(userModel.getFirst_name());
            }
            if (userModel.getLast_name() != null) {
                user.setLastName(userModel.getLast_name());
            }
            if (userModel.getPassword() != null) {
                user.setPassword(passwordManager.encryptPassword(userModel.getPassword()));
            }
            userDao.save(user);
        } else {
            throw new NoSuchElementException("Password doesn't match");
        }
    }
}
