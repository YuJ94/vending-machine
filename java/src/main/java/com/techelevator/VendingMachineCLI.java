package com.techelevator;

import com.techelevator.view.Balance;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;
import com.techelevator.view.VendingMachineItems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private Menu menu;
	private VendingMachine vm = new VendingMachine();

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String OPTIONS_MENU_FEED_MONEY = "Feed Money";
	private static final String OPTIONS_MENU_SELECT_PRODUCT = "Select Product";
	private static final String OPTIONS_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] OPTIONS_MENU_OPTIONS = { OPTIONS_MENU_FEED_MONEY, OPTIONS_MENU_SELECT_PRODUCT, OPTIONS_MENU_FINISH_TRANSACTION };

	private static Scanner userInput = new Scanner(System.in);


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	//todo indicate when product is sold out and update quantity
	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				display();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayVendingMachineOptions();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println();
				System.out.println("Thank you for stopping by!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void display() {

		for (Map.Entry<String, VendingMachineItems> items : vm.getInventory().entrySet()) {
			String slot = items.getKey();
			VendingMachineItems vmItem = items.getValue();

			System.out.println(slot + vmItem.getItemName() + vmItem.getItemPrice());
		}


	}

//
//	private static void displayVendingMachineItems() {
//		try (Scanner readFile = new Scanner(new File("vendingmachine.csv"))) {
//			System.out.println();
//			while(readFile.hasNextLine()) {
//
//				String line = readFile.nextLine();
//
//				String[] itemArray = line.split("\\|");
//
//				VendingMachineItems item = new VendingMachineItems(itemArray[0], itemArray[1], (Double.parseDouble((itemArray[2]))), itemArray[3]);
//
//				List<VendingMachineItems> itemsList = new ArrayList<>();
//
//				itemsList.add(item);
//
//				System.out.println(item.getItemSlot()+ ") " + item.getItemName() + " - $" + String.format("%.2f", item.getItemPrice()) + " - " + item.getQuantityRemaining() + " Available");
//				}
//
//		} catch (Exception e) {
//			System.out.println("ERROR FOUND:");
//			System.out.println("Please check the format of the input file.");
//			System.out.println("File should be read as a pipe | delimited file.");
//			System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
//		}
//	}



	private void displayVendingMachineOptions() {
		while (true) {
			System.out.println();
			System.out.format("Current Money Provided: $%.2f", currentBalance.getCurrentBalance());
			System.out.println();

			String choice = (String) menu.getChoiceFromOptions(OPTIONS_MENU_OPTIONS);

			if (choice.equals(OPTIONS_MENU_FEED_MONEY)) {
				insertMoney();
			} else if (choice.equals(OPTIONS_MENU_SELECT_PRODUCT)) {
				System.out.println("22");
			} else if (choice.equals(OPTIONS_MENU_FINISH_TRANSACTION)) {
				System.out.println("33");
				break;
			}
		}
	}


	Balance currentBalance = new Balance(0);


	private Balance insertMoney() {
		System.out.println();
		System.out.print("How much money would you like to deposit: ");
		String moneyDeposited = userInput.nextLine();

		double methodReturnMoneyDeposited = 0;

		try {
			methodReturnMoneyDeposited = Double.parseDouble(moneyDeposited);
		} catch (Exception e) {
			System.out.print("This is not a real bill. Try again for a non-zero integer number. Try again: ");
			moneyDeposited = userInput.nextLine();
		}




		if (methodReturnMoneyDeposited == 1 || methodReturnMoneyDeposited == 2 || methodReturnMoneyDeposited == 5 || methodReturnMoneyDeposited == 10 || methodReturnMoneyDeposited == 20) {
			currentBalance.deposit(methodReturnMoneyDeposited);
			return currentBalance;
		}

		System.out.print("XXWe don't accept this bill. Please deposit another bill: ");

		return currentBalance;

//		//int[] bills = {100, 50, 20, 10, 5, 2, 1};
//		int methodReturnMoneyDeposited = 0;
//
//		while (methodReturnMoneyDeposited == 0) {
//			try {
//				methodReturnMoneyDeposited = Integer.parseInt(moneyDeposited);
//				if (methodReturnMoneyDeposited == 0) {
//					System.out.print("0 is not a bill. Please deposit another bill: ");
//					moneyDeposited = userInput.nextLine();
//				}
//				if (methodReturnMoneyDeposited)
//
////				boolean isRealMoney = false;
////
////				for (int amount : bills) {
////					if (methodReturnMoneyDeposited == amount) {
////						isRealMoney = true;
////						currentBalance.deposit(methodReturnMoneyDeposited);
////					}
////				}
////				if (isRealMoney = false) {
////					System.out.print("We don't accept this bill. Please deposit another bill: ");
////					moneyDeposited = userInput.nextLine();
////				}
//			} catch (Exception e) {
//				System.out.print("This is not a real bill. Try again for a non-zero integer number. Try again: ");
//				moneyDeposited = userInput.nextLine();
//			}
//		}
//
//		return currentBalance;
	}



}
