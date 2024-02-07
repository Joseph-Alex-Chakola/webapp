package com.csye5.CloudComputing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    private String first_name;
    private String last_name;
    private String username;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp account_created;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp account_updated;
}
