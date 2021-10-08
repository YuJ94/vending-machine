package com.techelevator.view;

public class VendingMachineItems {

    //instance variables
    private String itemName;
    private double itemPrice;
    private String itemFoodType;
    private int quantityRemaining;

    //getters
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public String getItemFoodType() { return itemFoodType; }
    public int getQuantityRemaining() { return quantityRemaining; }

    //constructor
    public VendingMachineItems(String itemName, double itemPrice, String itemFoodType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemFoodType = itemFoodType;
        this.quantityRemaining = 2;
    }

    //methods
    public int subtractQuantity() {
        if (this.quantityRemaining <= 0) {
            //nothing happens
        } else {
            this.quantityRemaining--;
        }
        return this.quantityRemaining;
    }

}
