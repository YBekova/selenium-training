package ru.stqa.selenium.litercart.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class NewAccountCreationTest extends TestBase {


  @Test
  public void newAccountCreationTest() throws InterruptedException {
    driver.get("http://localhost/litecart/en/");


    String email = randomEmail();
    String password = "456789";

    createAccount(email, password);
    Thread.sleep(1000);

    logout();
    Thread.sleep(1000);

    login(email, password);
    Thread.sleep(1000);

    logout();
    Thread.sleep(1000);
  }

  private String randomEmail(){
        return RandomStringUtils.randomAlphabetic(8)+ "@gmail.com";
  }

  private void createAccount(String email, String password) {
    driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
    driver.findElement(By.name("firstname")).sendKeys("Jen");
    driver.findElement(By.name("lastname")).sendKeys("Tuman");
    driver.findElement(By.name("address1")).sendKeys("Michurin st.");
    driver.findElement(By.name("postcode")).sendKeys("05001");
    driver.findElement(By.name("city")).sendKeys("Alaska");
    //Country
    WebElement select = findElementByCssSelector(null,"select[name=country_code]");
    changeSelectValueJS(select, "US");
    //State
    select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name=zone_code]")));
    changeSelectValueJS(select, "AK");
    //*****
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).sendKeys("+0957618638");
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("confirmed_password")).sendKeys(password);
    driver.findElement(By.name("create_account")).click();
  }

  private void login(String email, String password) {
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  private void logout() {
    driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
  }
  private void changeSelectValueJS(WebElement elementSelect, String value){
    String jscode = "arguments[0].value='" + value + "'; var event = document.createEvent('Event');" +
            " event.initEvent('change', true, true); arguments[0].dispatchEvent(event)";
    ((JavascriptExecutor) driver).executeScript(jscode, elementSelect);
  }
}
