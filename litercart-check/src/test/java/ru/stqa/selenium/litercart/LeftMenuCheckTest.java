package ru.stqa.selenium.litercart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LeftMenuCheckTest extends TestBase {

  @Test
  public void leftMenuCheck() {

    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);
    ArrayList<String> leftMenuList = new ArrayList<>();
    List<WebElement> leftMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));

    for (WebElement element : leftMenu) {
      leftMenuList.add(element.getText());
    }

    for (int i = 0; i < leftMenuList.size(); i++) {

      driver.findElement(By.linkText(leftMenuList.get(i))).click();
      System.out.print(leftMenuList.get(i) + " - go to page: check");

      searchTag("h1");

      ArrayList<String> leftSubMenuList = new ArrayList<>();
      List<WebElement> subMenu = driver.findElements(By.xpath("//ul[@class='docs']/li"));

      if (subMenu.size() > 0) {
        for (WebElement element : subMenu) {
          leftSubMenuList.add(element.getText());
        }

        for (int n = 0; n < leftSubMenuList.size(); n++) {

          driver.findElement(By.linkText(leftSubMenuList.get(n))).click();
          System.out.print("  |-" + leftSubMenuList.get(n) + " - go to page: check");

          searchTag("h1");
        }
      }
    }
  }

  private void searchTag(String tag) throws NoSuchElementException {
    System.out.println("       tag \"" + tag + "\" " + driver.findElement(By.tagName(tag)).getText());
  }

}




