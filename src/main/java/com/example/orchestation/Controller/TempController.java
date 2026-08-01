package com.example.orchestation.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @Value("${tempUser}")
    String userName;

    @Value("${password}")
    String userPassword;

    @GetMapping("/user")
    public ResponseEntity<?> GetUser(){
        return ResponseEntity.status(HttpStatus.OK).body(" Username is " + userName
                + ", Password is " + userPassword);
    }

}
