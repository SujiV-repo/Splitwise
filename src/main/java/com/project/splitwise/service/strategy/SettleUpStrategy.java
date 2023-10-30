package com.project.splitwise.service.strategy;

import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}
