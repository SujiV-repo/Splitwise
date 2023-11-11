package com.project.splitwise.controller;

import com.project.splitwise.dto.UserExpenseDto;
import com.project.splitwise.exception.UserNotFoundException;
import com.project.splitwise.service.UserExpenseService;
import com.project.splitwise.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userexpense")
public class UserExpenseController {
    @Autowired
    private UserExpenseService userExpenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/{userId}/addexpense")
    public ResponseEntity createUserExpense(@PathVariable int userId, @RequestBody UserExpenseDto userExpenseDto) throws UserNotFoundException {
        userExpenseService.addUserExpense(userId, userExpenseDto);
        return new ResponseEntity<>("User Expense added!", HttpStatus.CREATED);
    }
}
