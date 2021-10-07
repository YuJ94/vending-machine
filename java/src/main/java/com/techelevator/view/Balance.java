package com.techelevator.view;

public class Balance {

    //instance variables
    private double currentBalance;

    //getters
    public double getCurrentBalance() { return currentBalance; }

    //constructor
    public Balance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    //derived methods
    public double deposit(double amountToDeposit) {
        return currentBalance += amountToDeposit;
    }
    public double purchase(double amountOfPurchase) {
        return currentBalance -= amountOfPurchase;
    }

}
