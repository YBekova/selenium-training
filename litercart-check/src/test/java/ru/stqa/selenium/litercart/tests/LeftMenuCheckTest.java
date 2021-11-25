package ru.stqa.selenium.litercart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class LeftMenuCheckTest extends TestBase {

  private int linksCount;
  private int docsCount;
  private String pageName;
  private WebElement row;
  private WebElement link;

  @Test
  public void leftMenuCheck() throws InterruptedException {

    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);

    linksCount = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();

    for (int i = 1; i <= linksCount; i++) {

      link = refreshPage(i);
      pageName = link.findElement(By.xpath(".//span[@class='name']")).getText();
      link.click();
      link = refreshPage(i);
      docsCount = link.findElements(By.xpath("./ul[@class='docs']/li[@id]")).size();

      if (docsCount > 0) {
        for (int j = 1; j <= docsCount; j++) {
          link = refreshPage(i);
          row = link.findElement(By.xpath("./ul[@class='docs']/li[@id][" + j + "]"));
          pageName = row.findElement(By.xpath(".//span[@class='name']")).getText();
          row.click();
          checkHeader(pageName);
          Thread.sleep(250);
        }
      } else {
        checkHeader(pageName);
      }
      Thread.sleep(500);
    }

  }

  private WebElement refreshPage(int i) {
    WebElement row = driver.findElement(By.id("box-apps-menu"));
    WebElement link = row.findElement(By.xpath("./li[@id='app-'][" + i + "]"));
    return link;
  }

  private void checkHeader(String pageName) {
    String h1;
    String res = "*** The page " + pageName;
    if (isElementPresent(By.xpath(".//td[@id='content']/h1"))) {
      h1 = driver.findElement(By.xpath(".//td[@id='content']/h1")).getText();
      res += " have a header " + h1 + ".";
    } else
      res += " not have header h1";

    System.out.println(res);
  }

  boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }


}




