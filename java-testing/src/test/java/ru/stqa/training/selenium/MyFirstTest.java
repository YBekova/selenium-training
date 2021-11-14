package ru.stqa.training.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyFirstTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void myFirstTest() {

    driver.get("http://google.com");
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("WebDriver");
    searchBox.submit();
    driver.quit();
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;

  }
}
