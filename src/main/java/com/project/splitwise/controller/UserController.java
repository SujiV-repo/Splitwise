package com.project.splitwise.controller;

import com.project.splitwise.dto.UserDto;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registeruser")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.registerUser(userDto);
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }
}
