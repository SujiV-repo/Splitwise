package com.project.splitwise.service.ServiceImplementation;

import com.project.splitwise.model.*;
import com.project.splitwise.repository.ExpenseRepository;
import com.project.splitwise.repository.GroupRepository;
import com.project.splitwise.repository.UserExpenseRepository;
import com.project.splitwise.repository.UserRepository;
import com.project.splitwise.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Autowired
    private ExpenseRepository expenseRepository;
    //Initialise method to insert all the values into database
    @Override
    public void initialise() {
        //Creating new group
        Group group = new Group();
        group.setName("Tracking Expenses");
        group.setDescription("To Track all the Expenses spent ");
        group.setDefaultCurrency(Currency.INR);
        Group savedGroup = groupRepository.save(group);

        //Creating user and saving into the group
        User a = User.builder()
                .name("A")
                .email("a@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User b = User.builder()
                .name("B")
                .email("b@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User c = User.builder()
                .name("C")
                .email("c@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User d = User.builder()
                .name("D")
                .email("d@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User e = User.builder()
                .name("E")
                .email("e@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User f = User.builder()
                .name("F")
                .email("f@email.com")
                .phoneNumber("123")
                .groups(List.of(savedGroup)).build();

        User savedUserA = userRepository.save(a);
        User savedUserB = userRepository.save(b);
        User savedUserC = userRepository.save(c);
        User savedUserD = userRepository.save(d);
        User savedUserE = userRepository.save(e);
        User savedUserF = userRepository.save(f);

        savedGroup.setUsers(List.of(savedUserA, savedUserB, savedUserC, savedUserD, savedUserE, savedUserF));
        savedGroup = groupRepository.save(savedGroup);

        //Entering expenses for each user
        UserExpense userExpenseA = new UserExpense();
        userExpenseA.setUserExpenseType(UserExpenseType.HADTOPAY);
        userExpenseA.setAmount(500);
        userExpenseA.setUser(savedUserA);
        UserExpense savedUserExpenseA = userExpenseRepository.save(userExpenseA);

        UserExpense userExpenseB = new UserExpense();
        userExpenseB.setUserExpenseType(UserExpenseType.HADTOPAY);
        userExpenseB.setAmount(2000);
        userExpenseB.setUser(savedUserB);
        UserExpense savedUserExpenseB = userExpenseRepository.save(userExpenseB);

        UserExpense userExpenseC = new UserExpense();
        userExpenseC.setUserExpenseType(UserExpenseType.HADTOPAY);
        userExpenseC.setAmount(500);
        userExpenseC.setUser(savedUserC);
        UserExpense savedUserExpenseC = userExpenseRepository.save(userExpenseC);

        UserExpense userExpenseD = new UserExpense();
        userExpenseD.setUserExpenseType(UserExpenseType.PAID);
        userExpenseD.setAmount(1500);
        userExpenseD.setUser(savedUserD);
        UserExpense savedUserExpenseD = userExpenseRepository.save(userExpenseD);

        UserExpense userExpenseE = new UserExpense();
        userExpenseE.setUserExpenseType(UserExpenseType.PAID);
        userExpenseE.setAmount(500);
        userExpenseE.setUser(savedUserE);
        UserExpense savedUserExpenseE = userExpenseRepository.save(userExpenseE);

        UserExpense userExpenseF = new UserExpense();
        userExpenseF.setUserExpenseType(UserExpenseType.PAID);
        userExpenseF.setAmount(1000);
        userExpenseF.setUser(savedUserF);
        UserExpense savedUserExpenseF = userExpenseRepository.save(userExpenseF);

        //saving every expense related to a user
        Expense expense = new Expense();
        expense.setDescription("Total Expenses for the month");
        expense.setAmount(3000);
        expense.setUserExpenses(List.of(savedUserExpenseA, savedUserExpenseB, savedUserExpenseC, savedUserExpenseD, savedUserExpenseE, savedUserExpenseF));
        expense.setCurrency(Currency.INR);
        Expense savedExpense = expenseRepository.save(expense);

        savedGroup.setExpenses(List.of(savedExpense));
        groupRepository.save(savedGroup);
    }
}
