package com.project.splitwise.repository;

import com.project.splitwise.model.User;
import com.project.splitwise.model.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Integer> {
    List<UserExpense> findByUserId(int userId);
}
