package com.example.authenticatingwithldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthenticatingWithLdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatingWithLdapApplication.class, args);
    }


    @GetMapping("/")
    public String index(){
        return "Welcome to the home page!";
    }
}
