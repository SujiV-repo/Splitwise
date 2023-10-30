package com.project.splitwise.service.strategy;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(){
        return new HeapBasedSettleUpStrategy();
    }
}
