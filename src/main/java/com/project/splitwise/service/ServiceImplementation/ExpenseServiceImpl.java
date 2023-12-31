package com.project.splitwise.service.ServiceImplementation;

import com.project.splitwise.dto.ExpenseDto;
import com.project.splitwise.exception.ExpenseNotFoundException;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.Group;
import com.project.splitwise.model.UserExpense;
import com.project.splitwise.model.UserExpenseType;
import com.project.splitwise.repository.ExpenseRepository;
import com.project.splitwise.repository.GroupRepository;
import com.project.splitwise.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    //Creating an expense for a particular existing group
    @Override
    public ExpenseDto createExpenseForGroup(int groupId, ExpenseDto expenseDto) throws GroupNotFoundException {
        Expense expense = modelMapper.map(expenseDto, Expense.class);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group Id : " + groupId + " is not present to create an expense for group"));


        List<Expense> expenses = group.getExpenses();
        if(expenses == null){
            expenses = new ArrayList<>();
        }
        expenses.add(expense);
        group.setExpenses(expenses);

        Expense savedexpense = expenseRepository.save(expense);
        groupRepository.save(group);

        return modelMapper.map(savedexpense, ExpenseDto.class);
    }

    //Fetching total amount for an Expense in the group
    @Override
    public double getTotalAmountForExpense(int expenseId) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with the given Id : " + expenseId + " is not created"));

        List<UserExpense> userExpenses = expense.getUserExpenses();
        double totalAmount = 0;

        for (UserExpense userExpense : userExpenses) {
            if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                totalAmount = totalAmount + userExpense.getAmount();
            }
        }

        expense.setAmount(totalAmount);
        expenseRepository.save(expense);

        return totalAmount;
    }


}
