package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {

    private VendingMachineItem testObject;

    @Before
    public void setup() {
        testObject = new VendingMachineItem("Doritos",2.00,"Chip");
    }

    @Test
    public void test_subtractQuantity_from_current_quantity() {
        //Act
        int result = testObject.subtractQuantity();

        //Assert
        Assert.assertEquals(4, result);
    }

    @Test
    public void test_foodType_food_sound() {
        //Act
        String result = testObject.foodType();

        //Assert
        Assert.assertEquals( "\n" + "Crunch Crunch, Yum!", result);
    }

}
