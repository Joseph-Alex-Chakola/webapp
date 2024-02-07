package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.service.DBHealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/healthz")
public class DbHealthController {
   DBHealthService dbHealthService;

    public DbHealthController(DBHealthService dbHealthService) {
        this.dbHealthService = dbHealthService;
    }

    @GetMapping
    @Operation(summary = "Health Endpoint", description = "Returns the database connection status.")
    @Tag(name = "Public", description = "Operations available to all users without authentication")
    public void checkHealth() throws SQLException {
        dbHealthService.checkHealth();
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void Service_Unavailable(SQLException e){
        System.out.println(e.getMessage());
    }

}
