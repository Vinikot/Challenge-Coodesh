package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create an implementation of a Rest API .
 * Expose an API. Feel free to explore possibilities/functionalities/capabilities following Rest standard.
 * We suggest that your implementation have at least a CRUD scenario.
 */
@SpringBootApplication
@RestController
public class TASK5 {

    public static void springInitializer(String[] args) {
        SpringApplication.run(TASK5.class, args);
    }

}