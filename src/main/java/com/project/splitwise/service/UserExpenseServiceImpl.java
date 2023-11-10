package com.project.splitwise.service;

import com.project.splitwise.dto.UserExpenseDto;
import com.project.splitwise.model.UserExpense;
import com.project.splitwise.repository.UserExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserExpenseServiceImpl implements UserExpenseService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Override
    public void addUserExpense(UserExpenseDto userExpenseDto) {
        UserExpense userExpense = modelMapper.map(userExpenseDto, UserExpense.class);
        userExpenseRepository.save(userExpense);
    }

    @Override
    public List<UserExpenseDto> getSingleUserExpenses(int userId) {
        List<UserExpense> userExpenses = userExpenseRepository.findByUserId(userId);
        return userExpenses.stream()
                .map(userExpense -> modelMapper.map(userExpense, UserExpenseDto.class))
                .collect(Collectors.toList());
    }
}
