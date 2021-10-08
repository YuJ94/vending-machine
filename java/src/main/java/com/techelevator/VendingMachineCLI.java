package com.techelevator;

import com.techelevator.view.Balance;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;
import com.techelevator.view.VendingMachineItems;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachineCLI {

	private Menu menu;
	private VendingMachine vm = new VendingMachine();

	private static Scanner userInput = new Scanner(System.in);

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String OPTIONS_MENU_FEED_MONEY = "Feed Money";
	private static final String OPTIONS_MENU_SELECT_PRODUCT = "Select Product";
	private static final String OPTIONS_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] OPTIONS_MENU_OPTIONS = { OPTIONS_MENU_FEED_MONEY, OPTIONS_MENU_SELECT_PRODUCT, OPTIONS_MENU_FINISH_TRANSACTION };

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayVendingMachineOptions();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println();
				System.out.println("Thank you for stopping by! Don't forget your change.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void displayItems() {
		System.out.println();
		for (Map.Entry<String, VendingMachineItems> items : vm.getCurrentInventory().entrySet()) {
			String slot = items.getKey();
			VendingMachineItems vmItem = items.getValue();

			System.out.printf("%-5s %-25s $%.2f\n", slot, vmItem.getItemName(), vmItem.getItemPrice()); //
		}
	}

	private void displayVendingMachineOptions() {
		while (true) {
			System.out.println();
			System.out.printf("Current Balance: $%.2f", currentBalance.getCurrentBalance());
			System.out.println();

			String choice = (String) menu.getChoiceFromOptions(OPTIONS_MENU_OPTIONS);

			if (choice.equals(OPTIONS_MENU_FEED_MONEY)) {
				insertMoney();
			} else if (choice.equals(OPTIONS_MENU_SELECT_PRODUCT)) {
				selectItem();
			} else if (choice.equals(OPTIONS_MENU_FINISH_TRANSACTION)) {
				System.out.println();
				System.out.println(spitOutChange(currentBalance));
				currentBalance.zeroBalance();
				break;
			}
		}
	}

	Balance currentBalance = new Balance(0);

	private Balance insertMoney() {
		System.out.println();
		System.out.print("How much money would you like to deposit: ");
		String userInputMoneyDeposited = userInput.nextLine();

		double moneyParsed = 0;

		while (moneyParsed == 0) {
			try {
				moneyParsed = Double.parseDouble(userInputMoneyDeposited);
				if (moneyParsed == 0) {
					System.out.print("0 is not a bill. Try again: ");
					userInputMoneyDeposited = userInput.nextLine();
				}
			} catch (Exception e) {
				System.out.print("This is not a bill. Try again for a non-zero integer number. Try again: ");
				userInputMoneyDeposited = userInput.nextLine();
			}
		}

		if (moneyParsed == 1 || moneyParsed == 2 || moneyParsed == 5 || moneyParsed == 10 || moneyParsed == 20) {
			currentBalance.deposit(moneyParsed);
		} else {
			System.out.println();
			System.out.print("We don't accept this bill. Try again.");
			System.out.println();
		}

		log(currentTime() + " FEED MONEY: " + String.format("$%.2f", moneyParsed) + " " + String.format("$%.2f", currentBalance.getCurrentBalance()));

		return currentBalance;
	}

	private void selectItem() {
		displayItems();

		System.out.println();
		System.out.print("Please select an item code: ");
		String userSelection = userInput.nextLine();

		boolean	isAKey = vm.getCurrentInventory().containsKey(userSelection);

		try {
			if (!isAKey) {
				System.out.println();
				System.out.print("This is not an invalid code. Try again.");
			}

			double userSelectionItemPrice = vm.getCurrentInventory().get(userSelection).getItemPrice();

			if (currentBalance.getCurrentBalance() >= userSelectionItemPrice) {
				if (userSelectionItemPrice > currentBalance.getCurrentBalance()) {
					System.out.println();
					System.out.print("You do not have enough money. Make another selection or deposit more money.");
					System.out.println();
				}
				if (vm.getCurrentInventory().get(userSelection).getQuantityRemaining() <= 0) {
					System.out.println();
					System.out.println("This item is SOLD OUT. Make another selection.");
				} else {
					currentBalance.purchase(userSelectionItemPrice);
					vm.getCurrentInventory().get(userSelection).subtractQuantity();
					System.out.println();
					System.out.println(vm.getCurrentInventory().get(userSelection).getItemName() + " " + vm.getCurrentInventory().get(userSelection).getItemPrice());
					System.out.println(foodType(userSelection));
					log(currentTime() + " " + vm.getCurrentInventory().get(userSelection).getItemName() + " " + userSelection + " " + String.format("$%.2f", (currentBalance.getCurrentBalance() + vm.getCurrentInventory().get(userSelection).getItemPrice())) + " " + String.format("$%.2f", currentBalance.getCurrentBalance()));
				}
			} else {
				System.out.println();
				System.out.print("You do not have enough money. Make a deposit.");
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println();
		}
	}

	private String foodType(String userIn) {
		String makeSound = "";

		if (vm.getCurrentInventory().get(userIn).getItemFoodType().equals("Chip")) {
			makeSound = "Crunch Crunch, Yum!";
		}
		if (vm.getCurrentInventory().get(userIn).getItemFoodType().equals("Candy")) {
			makeSound = "Munch Munch, Yum!";
		}
		if (vm.getCurrentInventory().get(userIn).getItemFoodType().equals("Drink")) {
			makeSound = "Glug Glug, Yum!";
		}
		if (vm.getCurrentInventory().get(userIn).getItemFoodType().equals("Gum")) {
			makeSound = "Chew Chew, Yum!";
		}

		return makeSound;
	}

	private String spitOutChange(Balance currentBalance) {

		double coins = currentBalance.getCurrentBalance();

		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		int pennies = 0;

		int numberOfQuarters = (int) (coins / .25);

		quarters += numberOfQuarters;

		double remainderAfterQuarters = coins % .25;

		int numberOfDimes = (int) (remainderAfterQuarters / .10);

		dimes += numberOfDimes;

		double remainderAfterDimes = remainderAfterQuarters % .10;

		int numberOfNickels = (int) (remainderAfterDimes / .05);

		nickels += numberOfNickels;

		double remainderAfterNickels = remainderAfterDimes % .05;

		int numberOfPennies = (int) (remainderAfterNickels / .01);

		pennies += numberOfPennies;

		log(currentTime() + " GIVE CHANGE: " + String.format("$%.2f", currentBalance.getCurrentBalance()) + " " + String.format("$%.2f", currentBalance.zeroBalance()));

		return "You received your change back: " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies.";

	}

	private void log(String input) {
		try (PrintWriter appendFile = new PrintWriter(new FileOutputStream("log.txt", true))) {
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
