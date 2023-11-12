package com.project.splitwise.service;

import com.project.splitwise.dto.ExpenseDto;
import com.project.splitwise.exception.ExpenseNotFoundException;
import com.project.splitwise.exception.GroupNotFoundException;

public interface ExpenseService {
    ExpenseDto createExpenseForGroup(int groupId, ExpenseDto expenseDto) throws GroupNotFoundException;

    double getTotalAmountForExpense(int expenseId) throws ExpenseNotFoundException;
}
