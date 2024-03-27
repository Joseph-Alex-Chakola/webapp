package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.Exception.UserAlreadyExistsException;
import com.csye5.CloudComputing.model.UserModel;
import com.csye5.CloudComputing.model.UserResponseModel;
import com.csye5.CloudComputing.model.UserUpdateModel;
import com.csye5.CloudComputing.service.UserCreationService;
import com.csye5.CloudComputing.service.UserOperationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/v1/user")
@AllArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserCreationService userCreationService;
    UserOperationServices userOperationServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user", description = "Create a user by giving First Name, Last Name, Username(email) and password")
    @Tag(name = "Public", description = "Operations available to all users without authentication")
    public UserResponseModel postPerson(@RequestBody(required = true) @Valid UserModel userModel) throws UserAlreadyExistsException {
        logger.info("Creating user");
        return userCreationService.createUser(userModel);
    }

    @GetMapping(value = "/self")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user information", description = "Get user information")
    @Tag(name = "Authenticated", description = "Operations Operations available only to authenticated users")
    public UserResponseModel getUserInformation(@RequestHeader("Authorization") String auth) throws AccessDeniedException {
        logger.info("Getting user information");
        return userOperationServices.getUserDetails(auth);
    }

    @PutMapping(value = "/self")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update user information", description = "Update user information")
    @Tag(name = "Authenticated", description = "Operations Operations available only to authenticated users")
    public void updateUserInformation(@RequestHeader("Authorization") String auth, @RequestBody(required = true) UserModel userModel) throws AccessDeniedException {
        logger.info("Updating user information");
        userOperationServices.updateUserDetails(auth, userModel);

    }
}
