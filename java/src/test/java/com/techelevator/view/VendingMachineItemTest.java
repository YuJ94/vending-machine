package com.techelevator.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {

    private VendingMachineItem testObject;

    @Before
    public void setup() {testObject =
            new VendingMachineItem("Doritos",2.00,"Chip");}


    @Test
    public void test_subtract__balance() {

        int result = testObject.subtractQuantity();

        Assert.assertEquals(4, result);
    }

    @Test
    public void test_make_food_sound() {

        String result = testObject.foodType();

        Assert.assertEquals( "\n" + "Crunch Crunch, Yum!", result);
    }




}
