package com.project.splitwise.service;

import com.project.splitwise.dto.UserExpenseDto;
import com.project.splitwise.model.UserExpense;

import java.util.List;

public interface UserExpenseService {
    void addUserExpense(UserExpenseDto userExpenseDto);

    List<UserExpenseDto> getSingleUserExpenses(int userId);
}
