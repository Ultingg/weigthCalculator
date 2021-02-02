package ru.isaykin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.isaykin")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }


}
