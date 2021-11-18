package ru.stqa.selenium.litercart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StickersTest extends TestBase{

  private List<WebElement> productList;
  private List<WebElement> sticksList;
  private int after;

  @Test
  public void stickersCheck() {

    driver.get("http://localhost/litecart/");

    productList = driver.findElements(By.cssSelector(".product"));

    for (WebElement element : productList) {

      System.out.println("Product: " + element.findElement(By.className("name")).getText());

      sticksList = element.findElements(By.cssSelector(".sticker"));

      if (sticksList.size() == 1) {
        System.out.println("1 sticker check \"" + element.findElement(By.cssSelector(".sticker")).getText() + "\" - check");
        after++;
      }

    }

    Assert.assertEquals(productList.size(), after);
  }
}


