package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineItemTest {

    private VendingMachineItem testObject;

    @Test
    public void test_subtractQuantity_from_current_quantity() {
        //Arrange
        testObject = new VendingMachineItem("Potato Crisps",3.05,"Chip");

        //Act
        int result = testObject.subtractQuantity();

        //Assert
        Assert.assertEquals(4, result);
    }

    @Test
    public void test_foodTypeSound_for_chip() {
        //Arrange
        testObject = new VendingMachineItem("Potato Crisps",3.05,"Chip");

        //Act
        String result = testObject.foodTypeSound();

        //Assert
        Assert.assertEquals( "\n" + "Crunch Crunch, Yum!", result);
    }

    @Test
    public void test_foodTypeSound_for_candy() {
        //Arrange
        testObject = new VendingMachineItem("Moonpie",1.80,"Candy");

        //Act
        String result = testObject.foodTypeSound();

        //Assert
        Assert.assertEquals( "\n" + "Munch Munch, Yum!", result);
    }

    @Test
    public void test_foodTypeSound_for_drink() {
        testObject = new VendingMachineItem("Cola",1.25,"Drink");

        //Act
        String result = testObject.foodTypeSound();

        //Assert
        Assert.assertEquals( "\n" + "Glug Glug, Yum!", result);
    }

    @Test
    public void test_foodTypeSound_for_gum() {
        //Arrange
        testObject = new VendingMachineItem("U-Chews",0.85,"Gum");

        //Act
        String result = testObject.foodTypeSound();

        //Assert
        Assert.assertEquals( "\n" + "Chew Chew, Yum!", result);
    }

}
