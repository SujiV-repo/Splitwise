package com.project.splitwise.controller;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.UserDto;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.exception.UserNotFoundException;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping("/{userId}/addGroup/{groupId}")
    public ResponseEntity addUserToGroup(@PathVariable int userId, @PathVariable int groupId) throws UserNotFoundException, GroupNotFoundException {
        userService.addUserToGroup(userId, groupId);
        return ResponseEntity.ok("User added to the Group successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable int userId) throws UserNotFoundException {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}/groups")
    public List<ResponseEntity> getGroupsForUser(@PathVariable int userId) throws UserNotFoundException {
        List<GroupDto> getGroups = userService.getUserGroups(userId);
        return getGroups.stream()
                .map(ResponseEntity::ok)
                .collect(Collectors.toList());
    }


}
