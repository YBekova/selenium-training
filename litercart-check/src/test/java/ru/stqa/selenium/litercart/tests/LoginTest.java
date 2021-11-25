package ru.stqa.selenium.litercart.tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends TestBase {

  @Test
  public void signIn() {

    logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);

  }

  public static void logInAdmin(String username, String password) {

    driver.navigate().to("http://localhost/litecart/admin");
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();

  }
}