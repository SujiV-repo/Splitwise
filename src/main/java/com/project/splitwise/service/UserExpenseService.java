package com.project.splitwise.service;

import com.project.splitwise.dto.UserExpenseDto;
import com.project.splitwise.exception.UserNotFoundException;
import com.project.splitwise.model.UserExpense;

import java.util.List;

public interface UserExpenseService {
    void addUserExpense(int userId, UserExpenseDto userExpenseDto) throws UserNotFoundException;

    List<UserExpenseDto> getSingleUserExpenses(int userId);
}
