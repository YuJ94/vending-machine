package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class VendingMachine {

    //instance variables
    private File inventoryFile = new File("C:\\Users\\Student\\Workspace\\mod1-capstone-blue-t10\\java\\vendingmachine.csv");
    private Map<String, VendingMachineItem> currentInventory = new LinkedHashMap<>();
    private Balance balance = new Balance(0);

    //getters
    public Map<String, VendingMachineItem> getCurrentInventory() { return currentInventory; }
    public Balance getBalance() { return balance; }

    //setters
    public void setBalance(Balance balance) { this.balance = balance; }

    //constructor
    public VendingMachine(File inventoryFile) {
        loadVendingMachineItems();
    }

    //methods
    public void loadVendingMachineItems() {
        try (Scanner readFile = new Scanner((inventoryFile))) {
            System.out.println();
            while(readFile.hasNextLine()) {

                String line = readFile.nextLine();

                String[] lineArray = line.split("\\|");

                String itemSlot = lineArray[0];

                VendingMachineItem itemInfo = new VendingMachineItem(lineArray[1], (Double.parseDouble((lineArray[2]))), lineArray[3]);

                currentInventory.put(itemSlot, itemInfo);
            }
        } catch (Exception e) {
            System.out.println("ERROR FOUND:" + e.getMessage());
            System.out.println();
            System.out.println("Please check the format of the input file.");
            System.out.println("File should be read as a pipe | delimited file.");
            System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
        }
    }

    public String displayItems() {
		String displayItems = "";

		for (Map.Entry<String, VendingMachineItem> items : getCurrentInventory().entrySet()) {
			String itemSlot = items.getKey();
			VendingMachineItem vmItem = items.getValue();

			if (vmItem.getQuantityRemaining() == 0) {
                displayItems += String.format(("%-5s %-25s $%.2f\n"), itemSlot, "SOLD OUT", vmItem.getItemPrice());
            } else {
                displayItems += String.format(("%-5s %-25s $%.2f\n"), itemSlot, vmItem.getItemName(), vmItem.getItemPrice());
            }

		}

		return displayItems;
	}

    public String depositMoney(String userInputMoneyDeposited) {
        double moneyParsed = 0;
        String depositMessage = "";

        while (moneyParsed == 0) {
            try {
                moneyParsed = Double.parseDouble(userInputMoneyDeposited);
                if (moneyParsed == 0) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        if (moneyParsed == 1 || moneyParsed == 2 || moneyParsed == 5 || moneyParsed == 10 || moneyParsed == 20) {
            balance.deposit(moneyParsed);

            log(currentTime() + " FEED MONEY: " + String.format("$%.2f", moneyParsed) + " " + String.format("$%.2f", balance.getCurrentBalance()));

            return depositMessage;
        } else {
            depositMessage = "\n" + "We don't accept this bill. Try again.";
        }

        return depositMessage;
    }

    public double getCurrentAmount() {
        return balance.getCurrentBalance();
    }

    public String itemSelection(String userSelection) {
        boolean	isAKey = getCurrentInventory().containsKey(userSelection);
        String itemSelectionMessage = "";
        VendingMachineItem userItemSelection = getCurrentInventory().get(userSelection);

        if (!isAKey) {
            itemSelectionMessage = "This is not a valid code. Try again.";
            return itemSelectionMessage;
        }

        double userSelectionItemPrice = userItemSelection.getItemPrice();

        if (getCurrentAmount() >= userSelectionItemPrice) {
            if (userItemSelection.getQuantityRemaining() <= 0) {
                itemSelectionMessage = ("This item is SOLD OUT. Make another selection.");
            } else {
                balance.purchase(userSelectionItemPrice);

                userItemSelection.subtractQuantity();

                itemSelectionMessage = (userItemSelection.getItemName() + " " + String.format("$%.2f", userItemSelection.getItemPrice()) + "\n" + userItemSelection.foodTypeSound());

                log(currentTime() + " " + userItemSelection.getItemName() + " " + userSelection + " " + String.format("$%.2f", (balance.getCurrentBalance() + userItemSelection.getItemPrice())) + " " + String.format("$%.2f", balance.getCurrentBalance()));
            }
        } else {
            itemSelectionMessage = ("You do not have enough money. Try again.");
        }

        return itemSelectionMessage;
    }

    public String spitOutChange() {
        double coins = balance.getCurrentBalance();
        DecimalFormat df2 = new DecimalFormat("###.##");

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        int numberOfQuarters = (int) (coins / .25);
        quarters += numberOfQuarters;
        double remainderAfterQuarters = coins % .25;
        double roundedRemainderAfterQuarters = Double.valueOf(df2.format(remainderAfterQuarters));

        int numberOfDimes = (int) (roundedRemainderAfterQuarters / .10);
        dimes += numberOfDimes;
        double remainderAfterDimes = (roundedRemainderAfterQuarters % .10);
        double roundedRemainderAfterDimes = Double.valueOf(df2.format(remainderAfterDimes));

        int numberOfNickels = (int) (roundedRemainderAfterDimes / .05);
        nickels += numberOfNickels;
        double remainderAfterNickels = (roundedRemainderAfterDimes % .05);
        double roundedRemainderAfterNickels = Double.valueOf(df2.format(remainderAfterNickels));

        int numberOfPennies = (int) (roundedRemainderAfterNickels / .01);
        pennies += numberOfPennies;

        log(currentTime() + " GIVE CHANGE: " + String.format("$%.2f", getCurrentAmount()) + " " + String.format("$%.2f", balance.zeroBalance()));

        return "You received your change back: " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies.";
    }

    public double resetToZeroBalance() {
        return balance.zeroBalance();
    }

    private void log(String input) {
        try (PrintWriter appendFile = new PrintWriter(new FileOutputStream("C:\\Users\\Student\\Workspace\\mod1-capstone-blue-t10\\java\\log.txt", true))) {
            appendFile.println(input);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private String currentTime() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        Date now = new Date(System.currentTimeMillis());
        return dateFormatter.format(now);
    }

}
