package com.project.splitwise.service.strategy;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(SettleUpStrategies settleUpStrategies){
        return switch (settleUpStrategies){
            case HeapBased_SettleUpStrategy -> new HeapBasedSettleUpStrategy();
        };
    }
}
