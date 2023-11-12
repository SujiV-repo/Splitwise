package com.project.splitwise.controller;

import com.project.splitwise.dto.ExpenseDto;
import com.project.splitwise.exception.ExpenseNotFoundException;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/{groupId}/create-expense")
    public ResponseEntity createExpenseForGroup(@PathVariable int groupId, @RequestBody ExpenseDto expenseDto) throws GroupNotFoundException {
        return new ResponseEntity<>(expenseService.createExpenseForGroup(groupId, expenseDto), HttpStatus.CREATED);
    }

    @GetMapping("/{expenseId}/getTotalAmount")
    public ResponseEntity GetTotalAmount(@PathVariable int expenseId) throws ExpenseNotFoundException {
        double amount = expenseService.getTotalAmountForExpense(expenseId);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }
}
