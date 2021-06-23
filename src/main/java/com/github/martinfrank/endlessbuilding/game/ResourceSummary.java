package com.github.martinfrank.endlessbuilding.game;

public class ResourceSummary {

    private final ResourecType resourceType;
    private double income;
    private double balance;


    public ResourceSummary(ResourecType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourecType getResourceType() {
        return resourceType;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
