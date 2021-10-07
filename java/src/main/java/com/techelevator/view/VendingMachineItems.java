package com.techelevator.view;

public class VendingMachineItems {

    //Each vending machine item has a Name and a Price.


    //instance variables
    private String itemSlot;
    private String itemName;
    private double itemPrice;
    private String itemFoodType;
    private int quantityRemaining;


    //getters
    public String getItemSlot() { return itemSlot; }
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public String getItemFoodType() { return itemFoodType; }
    public int getQuantityRemaining() { return quantityRemaining; }


    //constructor
    public VendingMachineItems(String itemSlot, String itemName, double itemPrice, String itemFoodType) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemFoodType = itemFoodType;
        this.quantityRemaining = 5;
    }

}
