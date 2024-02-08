package com.csye5.CloudComputing.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Hidden
public class UserModel {
    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @Email
    @NotNull
    private String username;

    @Size(min = 8, max = 30)
    @NotNull
    private String password;
}
