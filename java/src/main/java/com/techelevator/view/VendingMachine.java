package com.techelevator.view;

import java.io.File;
import java.util.*;

public class VendingMachine {

    //instance variables
    private Map<String, VendingMachineItems> currentInventory = new LinkedHashMap<>();

    //getters
    public Map<String, VendingMachineItems> getCurrentInventory() { return currentInventory; }

    //constructor
    public VendingMachine() {
        loadVendingMachineItems();
    }

    //methods
    public void loadVendingMachineItems() {
        try (Scanner readFile = new Scanner(new File("vendingmachine.csv"))) {
            System.out.println();
            while(readFile.hasNextLine()) {

                String line = readFile.nextLine();

                String[] itemArray = line.split("\\|");

                String slot = itemArray[0];

                VendingMachineItems item = new VendingMachineItems(itemArray[1], (Double.parseDouble((itemArray[2]))), itemArray[3]);

                currentInventory.put(slot, item);

            }
        } catch (Exception e) {
            System.out.println("ERROR FOUND:");
            System.out.println("Please check the format of the input file.");
            System.out.println("File should be read as a pipe | delimited file.");
            System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
        }
    }

}
