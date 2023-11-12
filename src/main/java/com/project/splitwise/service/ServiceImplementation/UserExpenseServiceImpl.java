package com.project.splitwise.service.ServiceImplementation;

import com.project.splitwise.dto.UserExpenseDto;
import com.project.splitwise.exception.ExpenseNotFoundException;
import com.project.splitwise.exception.UserExpenseNotFoundException;
import com.project.splitwise.exception.UserNotFoundException;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.model.UserExpense;
import com.project.splitwise.repository.ExpenseRepository;
import com.project.splitwise.repository.UserExpenseRepository;
import com.project.splitwise.repository.UserRepository;
import com.project.splitwise.service.UserExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserExpenseServiceImpl implements UserExpenseService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public void addUserExpense(int userId, UserExpenseDto userExpenseDto) throws UserNotFoundException {
        UserExpense userExpense = modelMapper.map(userExpenseDto, UserExpense.class);

        //getting user id from user repository
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("Cannot create expense as the user is not registered for the Id : "+ userId));
        userExpense.setUser(user);
        userExpenseRepository.save(userExpense);
    }

    @Override
    public List<UserExpenseDto> getSingleUserExpenses(int userId) {
        List<UserExpense> userExpenses = userExpenseRepository.findByUserId(userId);
        return userExpenses.stream()
                .map(userExpense -> modelMapper.map(userExpense, UserExpenseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addUserExpenseToExpense(int userExpenseId, int expenseId) throws UserExpenseNotFoundException, ExpenseNotFoundException {
        UserExpense userExpense = userExpenseRepository.findById(userExpenseId)
                .orElseThrow(() -> new UserExpenseNotFoundException("This user expense is not created for user expense Id : " + userExpenseId));

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException("This expense is not registered in the group. Please create new expense with Id : " + expenseId +" and add user expenses"));


        List<UserExpense> userExpenses = expense.getUserExpenses();
        if(userExpenses == null){
            userExpenses = new ArrayList<>();
        }

        userExpenses.add(userExpense);
        expense.setUserExpenses(userExpenses);
        expenseRepository.save(expense);
    }


}
