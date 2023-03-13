package com.nagp.microservices.loginApplication.controller;

import com.nagp.microservices.loginApplication.exception.UserLoginFailedException;
import com.nagp.microservices.loginApplication.pojo.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private  static final Map<String,String> USER_CREDENTIALS = new HashMap<>();

    static{
        USER_CREDENTIALS.put("abc@gmail.com","123XX");
        USER_CREDENTIALS.put("xyz@gmail.com","987XX");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginCredentials(@RequestBody User user){

        if(user == null || !USER_CREDENTIALS.containsKey(user.getEmail()) || !USER_CREDENTIALS.containsValue(user.getPassword())) {
            return ("Invalid Username or Password");
        }
       return "Login Successful";

    }
}
