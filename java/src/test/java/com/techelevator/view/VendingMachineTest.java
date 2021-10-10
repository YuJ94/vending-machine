package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class VendingMachineTest {

    private VendingMachine testObject;
    private File inventoryFile = new File("C:\\Users\\Student\\workspace\\mod1-capstone-blue-t10\\java\\vendingmachine.csv");

    @Before
    public void setup() {
        testObject = new VendingMachine(inventoryFile);
    }

    //displayItems METHOD TESTS
    @Test
    public void test_displayItems() {
        //Arrange
        String displayItems = "";

        for (Map.Entry<String, VendingMachineItem> items : testObject.getCurrentInventory().entrySet()) {
            String itemSlot = items.getKey();
            VendingMachineItem vmItem = items.getValue();

            displayItems += String.format(("%-5s %-25s $%.2f\n"), itemSlot, vmItem.getItemName(), vmItem.getItemPrice());
        }

        //Act
        String result = testObject.displayItems();

        //Assert
        Assert.assertEquals(displayItems, result);
    }

    //getMoneyParsed METHOD TESTS
    @Test
    public void test_getMoneyParsed_no_message_error() {
        //Act
        String result = testObject.getMoneyParsed("5");

        //Assert
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getMoneyParsed_message_error_with_0() {
        //Act
        String result = testObject.getMoneyParsed("0");

        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again.", result);
    }

    @Test
    public void test_getMoneyParsed_message_error_with_string() {
        //Act
        String result = testObject.getMoneyParsed("abc");

        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again.", result);
    }

    @Test
    public void test_getMoneyParsed_message_error_with_null() {
        //Act
        String result = testObject.getMoneyParsed(null);

        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again.", result);
    }

    @Test
    public void test_getMoneyParsed_message_error_with_invalid_bill() {
        //Act
        String result = testObject.getMoneyParsed("3");

        //Assert
        Assert.assertEquals("\n" + "We don't accept this bill. Try again.", result);
    }

    //getItemSelection METHOD TESTS
    @Test
    public void test_getItemSelection_dispense_item() {
        //Arrange
        testObject.getBalance().deposit(5);

        //Act
        String result = testObject.getItemSelection("D4");

        //Assert
        Assert.assertEquals("Triplemint $0.75" + "\n" + "\n" + "Chew Chew, Yum!", result);
    }

    @Test
    public void test_getItemSelection_message_error_with_string() {
        //Act
        String result = testObject.getItemSelection("abc");

        //Assert
        Assert.assertEquals("This is not a valid code. Try again.", result);
    }

    @Test
    public void test_getItemSelection_message_error_with_not_enough_money() {
        //Act
        String result = testObject.getItemSelection("D4");

        //Assert
        Assert.assertEquals("You do not have enough money. Try again.", result);
    }

    @Test
    public void test_getItemSelection_message_error_with_sold_out() {
        //Arrange
        testObject.getBalance().deposit(5);

        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");
        testObject.getItemSelection("D4");

        //Act
        String result = testObject.getItemSelection("D4");

        //Assert
        Assert.assertEquals("This item is SOLD OUT. Make another selection.", result);
    }

    //spitOutChange METHOD TESTS
    @Test
    public void test_spitOutChange_with_quarters_dimes_nickels() {
        //Arrange
        testObject.getBalance().deposit(2);

        testObject.getBalance().purchase(.85);

        //Act
        String result = testObject.spitOutChange();

        //Assert
        Assert.assertEquals("You received your change back: 4 quarters, 1 dimes, 1 nickels, 0 pennies.", result);
    }

}
