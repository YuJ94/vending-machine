package com.techelevator.view;

public class VendingMachineItems {
    //instance variables
    //Each vending machine item has a Name and a Price.
    private String name;
    private double price;
    private String slot;
    private String foodType;
    private int quantityRemaining;
    //getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantityRemaining() {return quantityRemaining;}
    public String getSlot() { return slot;}
    public String getFoodType() {return foodType;}
    //constructor

    public VendingMachineItems(String slot, String name, double price, String foodType) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.quantityRemaining = 5;
    }
}
