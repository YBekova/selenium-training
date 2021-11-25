package ru.stqa.selenium.litercart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddAndDeleteFromCartTest extends TestBase {

  @Test
  public void addAndDeleteFromCartTest() {

    driver.navigate().to("http://localhost/litecart/");

    for (int i = 0; i < 3; i++) {
      wait.until(titleIs("Online Store | My Store"));

      // find first item
      WebElement element = findElementByCssSelector(null, "div.name");
      Assert.assertNotNull("No products found on main page", element);

      // item name
      String productName = element.getText();
      element.click();

      //***** waiting for item page loading
      wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("div#box-product.box h1.title")), productName));

      // cart item quantity before addition
      int quantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
      element = findElementByCssSelector(null, ".options select");

      // check for Size option
      if (element != null) {
        findElementByCssSelector(element, "option[value='Small']").click();
      }

      // add to cart
      driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();

      // waiting for cart update
      wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("span.quantity")), String.valueOf(quantity + 1)));
      driver.navigate().back();
    }

    // click Checkout
    driver.findElement(By.cssSelector("div#cart a.link")).click();
    int itemCnt = driver.findElements(By.cssSelector("ul.items li")).size();

    // click first shortcut to stop items moving
    for (int i = 0; i < itemCnt; i++) {
      if (i == 0 && itemCnt > 1) {
        findElementByCssSelector(null, "ul.shortcuts > li").click();
      }

      // line counter in Order Summary table
      int lineCnt = driver.findElements(By.cssSelector("div#box-checkout-summary tr")).size();

      // click Remove button
      findElementByCssSelector(null, "li.item:nth-child(1) button[name='remove_cart_item']").click();

      // waiting for Order Summary table update (line number decrement)
      wait.until((WebDriver d) -> d.findElements(By.cssSelector("div#box-checkout-summary tr")).size() < lineCnt);
      System.out.println("items removed from cart");
    }
  }
}
