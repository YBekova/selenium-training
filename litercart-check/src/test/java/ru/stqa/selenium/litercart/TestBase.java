package ru.stqa.selenium.litercart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {


  static  WebDriver driver;
  static  WebDriverWait wait;

  public static final String LOGIN = "admin";
  public static final String PASSWORD = "admin";

  @Before
  public void start() {

    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, 10);

  }

  @After
  public void stop() {

    driver.quit();
    driver = null;

  }

}