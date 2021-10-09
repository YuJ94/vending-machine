package com.techelevator.view;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BalanceTest {

  private Balance testObject;

  @Before
    public void setup() {testObject = new Balance(0);}
  @After
    public void teardown() { testObject = new Balance(0); }

  @Test
  public void test_deposit_and_add_to_current_balance() {
    //Arrange
    //so in order to test we have to have an object
    //Act
     double result = testObject.deposit(5);
    //Assert
    Assert.assertEquals(5, result,.001);
  }
  @Test
  public void test_purchase_and_subtract_current_balance() {
    //Arrange
    //so in order to test we have to have an object
    //Act
    double deposit = testObject.deposit(4);
    double result = testObject.purchase(1);
    //Assert
    Assert.assertEquals(3, result,.001);
  }

  @Test
  public void test_zero_balance_from_current_balance() {
    //Arrange
    //so in order to test we have to have an object
    //Act
    double deposit = testObject.deposit(3);
    double result = testObject.zeroBalance();
    //Assert
    Assert.assertEquals(0, result,.001);
  }



}





