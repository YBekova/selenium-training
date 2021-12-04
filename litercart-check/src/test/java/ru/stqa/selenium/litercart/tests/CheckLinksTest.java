package ru.stqa.selenium.litercart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static ru.stqa.selenium.litercart.tests.TestBase.driver;

public class CheckLinksTest extends TestBase{

  @Test
  public void checkLinksTest(){

    driver.navigate().to("http://localhost/litecart/admin");
    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);

    driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
    wait.until(titleIs("Countries | My Store"));
    driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child(2) > td:nth-child(5) > a:nth-child(1)")).click();

    wait.until(titleIs("Edit Country | My Store"));
    List<WebElement> extLinks = driver.findElements(By.cssSelector("#content i.fa-external-link"));

    String parentWind = driver.getWindowHandle();
    Set<String> oldWnds = driver.getWindowHandles();
    for (WebElement link:extLinks) {
      link.click();

      //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
      String newWindowHnd = wait.until(new ExpectedCondition<String>() {
        @Override
        public String apply(WebDriver input) {
          Set<String> newWinds = input.getWindowHandles();
          newWinds.removeAll(oldWnds);
          return newWinds.size()>0 ? newWinds.iterator().next() : null;
        }
      });
      driver.switchTo().window(newWindowHnd);

      // for Firefox: waiting for page title
      wait.until(F ->  { return driver.getTitle().length()>0;});
      System.out.println("Visited page title is: " + driver.getTitle());
      driver.close();
      driver.switchTo().window(parentWind);
    }
  }

}
