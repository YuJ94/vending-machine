package com.techelevator;

import com.techelevator.view.Balance;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachineItems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private Menu menu;

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
				displayVendingMachineItems();
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


	private static void displayVendingMachineItems() {
		try (Scanner readFile = new Scanner(new File("vendingmachine.csv"))) {
			while(readFile.hasNextLine()) {

				String line = readFile.nextLine();

				String[] itemArray = line.split("\\|");

				VendingMachineItems item = new VendingMachineItems(itemArray[0], itemArray[1], (Double.parseDouble((itemArray[2]))), itemArray[3]);

				List<VendingMachineItems> itemsList = new ArrayList<>();

				itemsList.add(item);

				System.out.println(item.getItemSlot()+ ") " +item.getItemName()+ " - $" +item.getItemPrice()+ " - "+item.getQuantityRemaining() +" Available");
				}

		} catch (Exception e) {
			System.out.println("ERROR FOUND:");
			System.out.println("Please check the format of the input file.");
			System.out.println("File should be read as a pipe | delimited file.");
			System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
		}
	}



	private void displayVendingMachineOptions() {
		while (true) {
			System.out.println();
			System.out.println("Current Money Provided: " + currentBalance.getCurrentBalance());

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
		System.out.print("How much money would you like to deposit: ");
		String moneyDeposited = userInput.nextLine();

		int methodReturnMoneyDeposited = 0;

		while (methodReturnMoneyDeposited == 0) {
			try {
				methodReturnMoneyDeposited = Integer.parseInt(moneyDeposited);
				if (methodReturnMoneyDeposited == 0) {
					System.out.print("0 doesn't do anything. Please deposit another bill: ");
					moneyDeposited = userInput.nextLine();
				}
			} catch (Exception e) {
				System.out.print("This is not a bill. Try again for a non-zero integer number. Try again: ");
				moneyDeposited = userInput.nextLine();
			}
		}

		currentBalance.deposit(methodReturnMoneyDeposited);

		return currentBalance;
	}



}
