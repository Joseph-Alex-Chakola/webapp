package com.csye5.CloudComputing.model;


import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Hidden
public class UserUpdateModel {
    private String first_name;
    private String last_name;
    private String password;
}
