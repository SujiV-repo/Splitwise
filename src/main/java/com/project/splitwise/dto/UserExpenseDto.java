package com.project.splitwise.dto;

import com.project.splitwise.model.UserExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserExpenseDto {
    private double amount;
    private UserExpenseType userExpenseType;
}
