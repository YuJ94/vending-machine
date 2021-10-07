package com.techelevator.view;

public class Balance {

    //instance variables
    private int currentBalance;

    //getters
    public int getCurrentBalance() { return currentBalance; }

    //constructor
    public Balance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    //derived methods
    public int deposit(int amountToDeposit) {
        return currentBalance += amountToDeposit;
    }
    public int purchase(int amountOfPurchase) {
        return currentBalance -= amountOfPurchase;
    }

}
