package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.service.DBHealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/healthz")
public class DbHealthController {
   DBHealthService dbHealthService;
    private static final Logger logger = LoggerFactory.getLogger(DbHealthController.class);


    public DbHealthController(DBHealthService dbHealthService) {
        this.dbHealthService = dbHealthService;
    }

    @GetMapping
    @Operation(summary = "Health Endpoint", description = "Returns the database connection status.")
    @Tag(name = "Public", description = "Operations available to all users without authentication")
    public void checkHealth() throws SQLException {
        logger.info("Checking Database Health");
        dbHealthService.checkHealth();
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void Service_Unavailable(SQLException e){
       logger.error("Database Error: " + e.getMessage());
    }

}
