package com.project.splitwise.service.strategy;

import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.model.UserExpense;
import com.project.splitwise.model.UserExpenseType;

import java.util.*;

public class HeapBasedSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<TransactionDTO> settleUp(List<Expense> expenses) {
        HashMap<User, Double> outStandingAmountMap = new HashMap<>();
        List<TransactionDTO> transactions = new ArrayList<>();
        //looping through expenses and user expenses and creating a final outstanding amount for every user and storing it in the map
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User user = userExpense.getUser();
                double currentOutstandingAmount = outStandingAmountMap.getOrDefault(user, 0.00);
                outStandingAmountMap.put(user, getUpdatedOutStandingAmount(currentOutstandingAmount, userExpense));
            }
        }

        //after getting the outstanding amount, creating the heaps and storing the negative outstanding amount in min heap and positive outstanding amount in max heap
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>(
                (a,b) -> Double.compare(b.getValue(), a.getValue())
        );

        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        for(Map.Entry<User, Double> entry : outStandingAmountMap.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(entry);
            }
            else if(entry.getValue() > 0){
                maxHeap.add(entry);
            }
            else {
                System.out.println(entry.getKey().getName() + "'s already settled up");
            }
        }

        //Calculating the transactions till the heaps become empty
        while(!minHeap.isEmpty()){
            Map.Entry<User, Double> maxHasToPay = minHeap.poll();
            Map.Entry<User, Double> maxWillGetPaid = maxHeap.poll();

            TransactionDTO transactionDTO = new TransactionDTO(
                    maxHasToPay.getKey().getName(),
                    maxWillGetPaid.getKey().getName(),
                    Math.min(Math.abs(maxHasToPay.getValue()), maxWillGetPaid.getValue())
            );

            transactions.add(transactionDTO);

            double newBalance = maxHasToPay.getValue() + maxWillGetPaid.getValue();
            if (newBalance == 0) {
                System.out.println("Settled for : "+ maxHasToPay.getKey().getName());
            }
            else if(newBalance < 0){
                maxHasToPay.setValue(newBalance);
                minHeap.add(maxHasToPay);
            } else if (newBalance > 0) {
                maxWillGetPaid.setValue(newBalance);
                maxHeap.add(maxWillGetPaid);
            }
        }

        return transactions;
    }

    private Double getUpdatedOutStandingAmount(double currentOutstandingAmount, UserExpense userExpense) {
        if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
            currentOutstandingAmount = currentOutstandingAmount + userExpense.getAmount();
        }
        else{
            currentOutstandingAmount = currentOutstandingAmount - userExpense.getAmount();
        }

        return currentOutstandingAmount;
    }
}
