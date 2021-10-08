package com.techelevator.view;

import java.io.File;
import java.util.*;

public class VendingMachine {
    private Map<String,VendingMachineItems> inventory = new LinkedHashMap<>();

    public Map<String, VendingMachineItems> getInventory() {
        return inventory;
    }

    //private List<VendingMachineItems> itemsList = new ArrayList<>();

    public VendingMachine() {
        loadVendingMachineItems();
    }
    public void loadVendingMachineItems() {
        try (Scanner readFile = new Scanner(new File("vendingmachine.csv"))) {
            System.out.println();
            while(readFile.hasNextLine()) {

                String line = readFile.nextLine();

                String[] itemArray = line.split("\\|");

                VendingMachineItems item = new VendingMachineItems(itemArray[0], itemArray[1], (Double.parseDouble((itemArray[2]))), itemArray[3]);

                inventory.put(item.getItemSlot(),item);

              //  itemsList.add(item);


            }

        } catch (Exception e) {
            System.out.println("ERROR FOUND:");
            System.out.println("Please check the format of the input file.");
            System.out.println("File should be read as a pipe | delimited file.");
            System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
        }
    }



}
