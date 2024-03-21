package com.csye5.CloudComputing.service;

import com.csye5.CloudComputing.Exception.UserAlreadyExistsException;
import com.csye5.CloudComputing.controller.UserController;
import com.csye5.CloudComputing.dao.UserDao;
import com.csye5.CloudComputing.model.UserModel;
import com.csye5.CloudComputing.model.UserResponseModel;
import com.csye5.CloudComputing.repository.User;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCreationService {
    UserDao userDao;
    PasswordManager passwordManager;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserResponseModel createUser(UserModel userModel) throws UserAlreadyExistsException {
        if(userDao.findUserByUsername(userModel.getUsername()).isPresent()) {
            logger.error("User already exists");
            throw new UserAlreadyExistsException("User already exists");
        } else {
            User user = new User();
            user.setFirstName(userModel.getFirst_name());
            user.setLastName(userModel.getLast_name());
            user.setUsername(userModel.getUsername());

            user.setPassword(passwordManager.encryptPassword(userModel.getPassword()));

            User result = userDao.save(user);
            UserResponseModel userResponseModel = new UserResponseModel();

            userResponseModel.setId(result.getId());
            userResponseModel.setFirst_name(result.getFirstName());
            userResponseModel.setLast_name(result.getLastName());
            userResponseModel.setUsername(result.getUsername());
            userResponseModel.setAccount_created(result.getAccountCreated());
            userResponseModel.setAccount_updated(result.getAccountUpdated());
            logger.info("User created");
            return userResponseModel;
        }
    }

}
