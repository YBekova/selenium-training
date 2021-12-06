package ru.stqa.selenium.litercart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class TestBase {


  protected static WebDriver driver;
  protected static WebDriverWait wait;

  public static final String LOGIN = "admin";
  public static final String PASSWORD = "admin";
  protected int timeout = 3;

  @Before
  public void start() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 20);

  }

  @After
  public void stop() {

    driver.quit();
    driver = null;

  }

  protected WebElement findElementByCssSelector(WebElement parent, String cssSelector) {
    WebElement element;
    try {
      if (parent == null) {
        element = driver.findElement(By.cssSelector(cssSelector));
      } else {
        element = parent.findElement(By.cssSelector(cssSelector));
      }
    } catch (NoSuchElementException ex) {
      return null;
    }
    return element;
  }

  protected void searchAndClick(By locator, String text) {
    List<WebElement> list = driver.findElements(locator);
    String name;
    for (WebElement we : list) {
      name = we.getText();
      if (name.equals(text)) {
        we.click();
        break;
      }
    }
  }
  protected void implicitlyWaitOn() {
    driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
  }

  protected void implicitlyWaitOff() {
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  }
}