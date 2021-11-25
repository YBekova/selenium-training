package ru.stqa.selenium.litercart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static ru.stqa.selenium.litercart.tests.TestBase.driver;

public class AddNewProductTest extends TestBase {

  @Test
  public void addNewProductTest() throws InterruptedException {
    //***************** Login *********************
    driver.navigate().to("http://localhost/litecart/admin");
    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);

    //***************** page Catalog *********************
    searchAndClick("ul#box-apps-menu li#app-", "Catalog");
    Thread.sleep(1000);

    //***************** button Add New Product *********************
    searchAndClick("td#content a.button", "Add New Product");
    Thread.sleep(1000);

    String newItem = "Duck in bathrobe";

    String relativePath = "./src/test/java/ru/stqa/selenium/litercart/recources/Duck.jpg";
    Path filePath = Paths.get(relativePath);
    String absolutePath = filePath.normalize().toAbsolutePath().toString();

    // filling General
    fillTabGeneral(newItem, absolutePath);

    // filling Information
    searchAndClick("div.tabs li", "Information");
    Thread.sleep(1000);
    fillTabInformation();

    // filling Prices
    searchAndClick("div.tabs li", "Prices");
    Thread.sleep(1000);
    fillTabPrices();

    // Save
    driver.findElement(By.cssSelector("button[name=save]")).click();
    Thread.sleep(1000);

    checkNewItem(newItem);

  }

  private void fillTabGeneral(String item, String path){

    // Name
    driver.findElement(By.name("name[en]")).sendKeys(item);

    // Status
    driver.findElement(By.cssSelector("input[name=status][value='1']")).click();

    // Code
    driver.findElement(By.name("code")).sendKeys("rp001");

    // Categories
    driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
    driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();

    // Quantity
    driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
    driver.findElement(By.name("quantity")).sendKeys("50");

    // Upload file
    driver.findElement(By.name("new_images[]")).sendKeys(path);
  }

  private void fillTabInformation() {
    // Manufacrurer
    Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
    manufact.selectByVisibleText("ACME Corp.");
    //Short Description
    driver.findElement(By.name("short_description[en]")).sendKeys("Duck, just a duck for bath or else...");
    //Description
    driver.findElement(By.className("trumbowyg-editor")).sendKeys("You can use this fun duck for your bath time or else.");
  }

  private void fillTabPrices() {
    // Purchase Price
    driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
    driver.findElement(By.name("purchase_price")).sendKeys("10");
    Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
    curr_code.selectByVisibleText("Euros");
    // Price
    driver.findElement(By.name("prices[USD]")).sendKeys("23");
  }

  private void checkNewItem(String item) {
    String res = "not";
    String name;
    WebElement root = driver.findElement(By.cssSelector("table.dataTable tbody"));
    List<WebElement> list = root.findElements(By.xpath(".//tr/td[3]/a"));
    for (WebElement we : list) {
      name = we.getText();
      if (name.equals(item) ) {
        res = "successfully";
        break;
      }
    }
    System.out.println("New item " + item + " " + res + " added");
  }

  private void searchAndClick(String linkList, String text) {
    List<WebElement> list = driver.findElements(By.cssSelector(linkList));
    String name;
    for (WebElement we : list) {
      name = we.getText();
      if (name.equals(text) ) {
        we.click();
        break;
      }
    }
  }
}
