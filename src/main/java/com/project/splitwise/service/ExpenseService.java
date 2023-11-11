package com.project.splitwise.service;

import com.project.splitwise.dto.ExpenseDto;
import com.project.splitwise.exception.GroupNotFoundException;

public interface ExpenseService {
    ExpenseDto createExpenseForGroup(int groupId, ExpenseDto expenseDto) throws GroupNotFoundException;
}
