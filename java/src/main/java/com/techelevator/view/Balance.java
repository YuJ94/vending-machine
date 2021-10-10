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

    //methods
    public double deposit(double amountToDeposit) {
        return currentBalance += amountToDeposit;
    }

    public double purchase(double amountOfPurchase) {
        return currentBalance -= amountOfPurchase;
    }

    public double zeroBalance() {
        currentBalance = 0;
        return currentBalance;
    }

}
