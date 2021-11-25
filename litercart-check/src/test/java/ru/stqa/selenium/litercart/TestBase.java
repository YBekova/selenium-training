package ru.stqa.selenium.litercart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {


  protected static  WebDriver driver;
  protected static  WebDriverWait wait;

  public static final String LOGIN = "admin";
  public static final String PASSWORD = "admin";

  @Before
  public void start() {

    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, 20);

  }

  @After
  public void stop() {

    driver.quit();
    driver = null;

  }
  protected WebElement findElementByCssSelector(WebElement parent, String cssSelector){
    WebElement element;
    try{
      if(parent == null) {
        element = driver.findElement(By.cssSelector(cssSelector));
      }else{
        element = parent.findElement(By.cssSelector(cssSelector));
      }
    }catch (NoSuchElementException ex){
      return null;
    }
    return  element;
  }

}