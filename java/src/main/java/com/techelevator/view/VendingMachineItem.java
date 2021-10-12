package com.techelevator.view;

public class VendingMachineItem {

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
    public VendingMachineItem(String itemName, double itemPrice, String itemFoodType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemFoodType = itemFoodType;
        this.quantityRemaining = 5;
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

    public String foodTypeSound() {
        String makeSound = "";

        if (getItemFoodType().equals("Chip")) {
            makeSound = "Crunch Crunch, Yum!";
        }
        if (getItemFoodType().equals("Candy")) {
            makeSound = "Munch Munch, Yum!";
        }
        if (getItemFoodType().equals("Drink")) {
            makeSound = "Glug Glug, Yum!";
        }
        if (getItemFoodType().equals("Gum")) {
            makeSound = "Chew Chew, Yum!";
        }

        return "\n" + makeSound;
    }

}
