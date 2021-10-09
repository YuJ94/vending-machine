package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class VendingMachineTest {

    private VendingMachine testObject;
    private File inventoryFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-blue-t10\\vendingmachine.csv");


    @Before
    public void setup() {testObject =
            new VendingMachine(inventoryFile);
    }


    //getMoneyParsed METHOD TESTS
    @Test
    public void get_money_parsed_test_no_message_error_() {
        //if the money can be parsed no error message will return to the user.
        String result = testObject.getMoneyParsed("5");
        //Assert
        Assert.assertEquals("", result);
    }

    @Test
    public void get_money_parsed_test_0_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getMoneyParsed("0");
        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again" + "\n", result);
    }
    @Test
    public void get_money_parsed_test_random_char_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getMoneyParsed("abc");
        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again" + "\n", result);
    }

    @Test
    public void get_money_parsed_test_null_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getMoneyParsed(null);
        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again" + "\n", result);
    }

    @Test
    public void get_money_parsed_test_invalid_dollar_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getMoneyParsed("3");
        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again" + "\n", result);
    }
    //getItemSelection METHOD TESTS
    @Test
    public void get_item_selection_test_random_char_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getItemSelection("abc");
        //Assert
        Assert.assertEquals("This is not a valid code. Try again.", result);
    }

    @Test
    public void get_item_selection_test_not_enough_money_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        String result = testObject.getItemSelection("D4");
        //Assert
        Assert.assertEquals("You do not have enough money. Make a deposit.", result);
    }

    @Test
    public void get_item_selection_test_sold_out_money_message_error_() {
        //if the money CANNOT be parsed no error message will return to the user.
        testObject.getBalance().deposit(5);
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        String result = testObject.getItemSelection("D4");

        //Assert
        Assert.assertEquals("This item is SOLD OUT. Make another selection.", result);
    }

    @Test
    public void get_item_selection_test_dispense_item_() {
        //if the money CANNOT be parsed no error message will return to the user.
        testObject.getBalance().deposit(5);
        String result = testObject.getItemSelection("D4");
        //Assert
        Assert.assertEquals("Triplemint $0.75" + "\n" + "\n" + "Chew Chew, Yum!", result);
    }
    //spitOutChange METHOD TESTS
    @Test
    public void spit_out_change_test_change_due_with_dimes_nickels() {
        //if the money CANNOT be parsed no error message will return to the user.
        testObject.getBalance().deposit(2);
        testObject.getBalance().purchase(.85);
        //You received your change back: 4 quarters, 1 dimes, 0 nickels, 4 pennies.
        String result = testObject.spitOutChange();
        //Assert
        Assert.assertEquals("You received your change back: 4 quarters, 1 dimes, 1 nickels, 0 pennies.", result);
    }
    //displayItems METHOD TESTS
    @Test
    public void _test_display_items_() {
        String display = "";
        for (Map.Entry<String, VendingMachineItem> items : testObject.getCurrentInventory().entrySet()) {
            String slot = items.getKey();
            VendingMachineItem vmItem = items.getValue();

            display += String.format(("%-5s %-25s $%.2f\n"),slot, vmItem.getItemName(), vmItem.getItemPrice()); //
        }
//       Map<String, VendingMachineItem> testMap = new LinkedHashMap<>();
//       //testMap.put(
        String result = testObject.displayItems();
        //Assert
        Assert.assertEquals(display, result);
    }



}
