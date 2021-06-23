package com.github.martinfrank.endlessbuilding.game;

public class ResourceSummary {

    public final String resourceType;
    public double income;
    public double balance;


    public ResourceSummary(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
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
