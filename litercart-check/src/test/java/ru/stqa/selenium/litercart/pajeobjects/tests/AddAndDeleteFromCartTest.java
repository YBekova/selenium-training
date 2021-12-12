package ru.stqa.selenium.litercart.pajeobjects.tests;

import org.junit.Test;

public class AddAndDeleteFromCartTest extends TestBase {

  @Test
  public void mainTest()  {

    app.mainPage.open();
    for (int i = 1; i<=3; i++) {
      app.mainPage.chooseFirstItem();
      app.itemPage.addItemToCart();
      app.itemPage.goToMainPage();
    }
    app.cartPage.open();
    app.cartPage.clearCart();

  }

}
