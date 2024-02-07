package com.csye5.CloudComputing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@OpenAPIDefinition
public class CloudComputingApplication {

	public static void main(String[] args) {

			SpringApplication.run(CloudComputingApplication.class, args);
	}


}
