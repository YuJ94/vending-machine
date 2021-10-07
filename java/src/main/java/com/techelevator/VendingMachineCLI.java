package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachineItems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;

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
				// do purchase
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
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
				//System.out.println(line);
				String[] array = line.split("\\|");
				VendingMachineItems item = new VendingMachineItems(array[0],array[1],(Double.parseDouble((array[2]))),array[3]);

				List<VendingMachineItems> items = new ArrayList<>();
				items.add(item);

				System.out.println(item.getSlot()+ ") " +item.getName()+ " - $" +item.getPrice()+ " - "+item.getQuantityRemaining() +" Available");
				}


		} catch (Exception e) {
			System.out.println("ERROR:");
			System.out.println("Please check the format of the input file.");
			System.out.println("File should be read as a pipe | delimited file.");
			System.out.println("The format should be \"Slot Location\" | \"Product Name\" | \"Price\" | \"Type\".");
		}
	}
}
