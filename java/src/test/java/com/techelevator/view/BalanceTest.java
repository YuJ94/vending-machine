package com.techelevator.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BalanceTest {

    private Balance testObject;

    @Before
    public void setup() {
        testObject = new Balance(0);
    }

    @After
    public void teardown() {
        testObject = new Balance(0);
    }

    @Test
    public void test_deposit_and_add_to_current_balance() {
        //Act
        double result = testObject.deposit(5);

        //Assert
        Assert.assertEquals(5, result,.001);
    }

    @Test
    public void test_purchase_and_subtract_from_current_balance() {
        //Arrange
        double deposit = testObject.deposit(4);

        //Act
        double result = testObject.purchase(1);

        //Assert
        Assert.assertEquals(3, result,.001);
    }

    @Test
    public void test_zeroBalance_from_current_balance() {
        //Arrange
        double deposit = testObject.deposit(3);

        //Act
        double result = testObject.zeroBalance();

        //Assert
        Assert.assertEquals(0, result,.001);
    }

}





